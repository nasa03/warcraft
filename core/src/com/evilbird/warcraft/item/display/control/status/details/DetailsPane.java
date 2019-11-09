/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.status.details;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.Grid;
import com.evilbird.warcraft.item.common.resource.ResourceType;
import com.evilbird.warcraft.item.display.control.status.details.building.BuildingDetailsPane;
import com.evilbird.warcraft.item.display.control.status.details.combatant.CombatantDetailsPane;
import com.evilbird.warcraft.item.display.control.status.details.combatant.CombatantTitlePane;
import com.evilbird.warcraft.item.display.control.status.details.combatant.SpellCasterDetailsPane;
import com.evilbird.warcraft.item.display.control.status.details.common.UnitTitlePane;
import com.evilbird.warcraft.item.display.control.status.details.resource.ResourceDetailsPane;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.ResourceExtractor;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.SpellCaster;
import com.evilbird.warcraft.item.unit.resource.Resource;

import static com.evilbird.warcraft.item.common.query.UnitOperations.isCorporeal;
import static com.evilbird.warcraft.item.common.query.UnitOperations.isResource;

/**
 * Represents a user interface control that displays the icon and name of an
 * {@link Item}. For some Items a selection of pertinent details is also
 * shown. For example the gold remaining in a gold mine or the attack strength
 * of a footman.
 *
 * @author Blair Butterworth
 */
public class DetailsPane extends Grid
{
    private com.evilbird.warcraft.item.display.control.status.details.common.UnitTitlePane generalTitle;
    private CombatantTitlePane combatantTitle;
    private com.evilbird.warcraft.item.display.control.status.details.building.BuildingDetailsPane buildingDetails;
    private CombatantDetailsPane combatantDetails;
    private SpellCasterDetailsPane spellCasterDetails;
    private ResourceDetailsPane resourceDetails;

    public DetailsPane(Skin skin) {
        super(1, 2);

        generalTitle = new UnitTitlePane(skin);
        combatantTitle = new CombatantTitlePane(skin);
        buildingDetails = new BuildingDetailsPane(skin);
        combatantDetails = new CombatantDetailsPane(skin);
        spellCasterDetails = new SpellCasterDetailsPane(skin);
        resourceDetails = new ResourceDetailsPane(skin);

        setSkin(skin);
        setSize(176, 176);
        setCellPadding(8);
        setTouchable(Touchable.disabled);
    }

    @Override
    public void setSkin(Skin skin) {
        super.setSkin(skin);
        com.evilbird.warcraft.item.display.control.status.details.DetailsPaneStyle style = skin.get(DetailsPaneStyle.class);
        setBackground(style.background);
    }

    public void setConstructing(Building building, boolean constructing) {
        if (isShown(buildingDetails)) {
            buildingDetails.setConstructing(building, constructing);
        }
    }

    public void setProducing(Building building, boolean producing) {
        if (isShown(buildingDetails)) {
            buildingDetails.setProducing(building, producing);
        }
    }

    public void setResource(ResourceType resource, float value) {
        if (isShown(buildingDetails)) {
            buildingDetails.setResource(resource, value);
        }
    }

    public void setResource(Item item, ResourceType resource, float value) {
        if (isShown(resourceDetails)) {
            resourceDetails.setResource(item, resource, value);
        }
    }

    public void setItem(Item item) {
        clearItems();
        showDetails(item);
    }

    private void showDetails(Item item) {
        setTitle(item);
        if (isCorporeal(item) || isResource(item)) {
            setDetails(item);
        }
    }

    private void setTitle(Item item) {
        if (item instanceof Combatant) {
            setTitle(combatantTitle, item);
        } else if (item instanceof Unit) {
            setTitle(generalTitle, item);
        }
    }

    private void setTitle(DetailsPaneElement view, Item item) {
        view.setItem(item);
        Cell cell = add(view);
        cell.expandX();
        cell.fillX();
    }

    private void setDetails(Item item) {
        if (item instanceof Resource || item instanceof ResourceExtractor) {
            setDetails(resourceDetails, item);
        } else if (item instanceof Building) {
            setDetails(buildingDetails, item);
        } else if (item instanceof SpellCaster) {
            setDetails(spellCasterDetails, item);
        } else if (item instanceof Combatant) {
            setDetails(combatantDetails, item);
        }
    }

    private void setDetails(DetailsPaneElement view, Item item) {
        view.setItem(item);
        view.setSize(160, 100);
        add(view);
    }
}