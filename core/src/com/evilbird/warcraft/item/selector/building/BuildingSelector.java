/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector.building;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.GridPoint2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemBasic;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.engine.item.spatial.ItemGraph;
import com.evilbird.engine.item.spatial.ItemNode;
import com.evilbird.engine.item.utility.ItemOperations;
import com.evilbird.warcraft.action.common.create.CreateEvent;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.action.move.MoveEvent;
import com.evilbird.warcraft.item.selector.SelectorType;

import java.util.Collection;

import static com.evilbird.engine.common.collection.CollectionUtils.containsAll;
import static com.evilbird.engine.common.collection.CollectionUtils.containsAny;
import static com.evilbird.engine.common.collection.CollectionUtils.containsEqual;
import static com.evilbird.engine.common.collection.CollectionUtils.flatten;
import static com.evilbird.engine.item.utility.ItemPredicates.hasType;
import static com.evilbird.warcraft.item.common.query.UnitPredicates.associatedWith;
import static com.evilbird.warcraft.item.layer.LayerType.Map;
import static com.evilbird.warcraft.item.layer.LayerType.Sea;
import static com.evilbird.warcraft.item.layer.LayerType.Shore;
import static com.evilbird.warcraft.item.selector.SelectorType.DockyardSelector;
import static com.evilbird.warcraft.item.selector.SelectorType.FoundrySelector;
import static com.evilbird.warcraft.item.selector.SelectorType.MetalworksSelector;
import static com.evilbird.warcraft.item.selector.SelectorType.OilPlatformSelector;
import static com.evilbird.warcraft.item.selector.SelectorType.OilRefinerySelector;
import static com.evilbird.warcraft.item.selector.SelectorType.OilRigSelector;
import static com.evilbird.warcraft.item.selector.SelectorType.RefinerySelector;
import static com.evilbird.warcraft.item.selector.SelectorType.ShipyardSelector;
import static com.evilbird.warcraft.item.unit.UnitType.OilPatch;

/**
 * The visual representation of a building before construction, allowing the
 * user to move it to the correct location and ensure it doesn't overlap an
 * existing structure.
 *
 * @author Blair Butterworth
 */
public class BuildingSelector extends ItemBasic
{
    private transient Skin skin;
    private transient Drawable building;
    private transient Drawable overlay;
    private transient Drawable allowed;
    private transient Drawable blocked;
    private transient boolean isClear;
    private transient EventQueue events;
    private transient SelectorType type;

    public BuildingSelector(Skin skin) {
        this.skin = skin;
        setStyle("default");
    }

    public boolean isClear() {
        return isClear;
    }

    public void setEvents(EventQueue events) {
        this.events = events;
    }

    public void setStyle(String name) {
        BuildingSelectorStyle style = skin.get(name, BuildingSelectorStyle.class);
        building = style.building;
        allowed = style.allowed;
        blocked = style.prohibited;
        overlay = isClear ? allowed : blocked;
    }

    @Override
    public void setRoot(ItemRoot root) {
        super.setRoot(root);
        evaluateOccupation(root);
    }

    public void setSize(GridPoint2 size) {
        super.setSize(size.x, size.y);
    }

    @Override
    public void setType(Identifier type) {
        super.setType(type);
        this.type = (SelectorType)type;
    }

    @Override
    public void positionChanged() {
        evaluateOccupation(getRoot());
    }

    @Override
    public void draw(Batch batch, float alpha) {
        float x = getX();
        float y = getY();
        float width = getWidth();
        float height = getHeight();
        building.draw(batch, x, y, width, height);
        overlay.draw(batch, x, y, width, height);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        evaluateEvents(events.getEvents(CreateEvent.class));
        evaluateEvents(events.getEvents(RemoveEvent.class));
        evaluateEvents(events.getEvents(MoveEvent.class));
    }

    private void evaluateEvents(Collection<? extends Event> events) {
        for (Event event: events) {
            if (ItemOperations.overlaps(this, event.getSubject())) {
                evaluateOccupation(getRoot());
            }
        }
    }

    private void evaluateOccupation(ItemRoot root) {
        if (root != null) {
            ItemGraph graph = root.getSpatialGraph();
            Collection<ItemNode> nodes = graph.getNodes(getPosition(), getSize());
            Collection<Item> items = flatten(nodes, ItemNode::getOccupants);
            isClear = isUnoccupied(items);
            overlay = isClear ? allowed : blocked;
        }
    }

    private boolean isUnoccupied(Collection<Item> items) {
        if (isOilPatchBased(type)) {
            return containsAll(items, hasType(OilPatch, Sea).or(associatedWith(this)))
                && containsEqual(items, hasType(OilPatch), hasType(Sea));
        }
        if (isShoreBased(type)) {
            return containsAll(items, hasType(Shore, Sea).or(associatedWith(this)))
                && containsAny(items, hasType(Shore));
        }
        return containsAll(items, hasType(Map).or(associatedWith(this)));
    }

    private boolean isShoreBased(SelectorType type) {
        return type == ShipyardSelector || type == RefinerySelector || type == FoundrySelector
            || type == DockyardSelector || type == OilRefinerySelector || type == MetalworksSelector;
    }

    private boolean isOilPatchBased(SelectorType type) {
        return type == OilPlatformSelector || type == OilRigSelector;
    }
    
}