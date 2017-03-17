package com.evilbird.warcraft.item.hud.action;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.NamedIdentifier;
import com.evilbird.engine.common.lang.Objects;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.control.GridPane;
import com.evilbird.warcraft.action.ActionType;
import com.evilbird.warcraft.item.hud.building.BuildSite;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ActionPane extends GridPane
{
    private ActionButtonProvider buttonProvider;
    private Collection<Item> selection;

    @Inject
    public ActionPane(ActionButtonProvider buttonProvider)
    {
        super(3, 3);
        this.buttonProvider = buttonProvider;
        this.selection = Collections.emptyList();

        setSize(176, 176);
        setCellPadding(3);
        setCellWidthMinimum(54);
        setCellHeightMinimum(46);
        setId(new NamedIdentifier("ActionPane"));
        setType(new NamedIdentifier("ActionPane"));
        setTouchable(Touchable.childrenOnly);
    }

    public void setSelection(Collection<Item> newSelection)
    {
        if (! Objects.equals(selection, newSelection)){
            selection = newSelection;
            clearCells();

            if (isBuildSiteSelected(selection)){
                showCancelAction();
            }
            else {
                showUnitActions(selection);
            }
        }
    }

    private boolean isBuildSiteSelected(Collection<Item> selection)
    {
        for (Item item: selection){
            if (item instanceof BuildSite){
                return true;
            }
        }
        return false;
    }

    private void showCancelAction()
    {
        ActionButton cancelButton = getButton(ActionType.Cancel);
        setCell(cancelButton, 2, 2);
    }

    private void showUnitActions(Collection<Item> selection)
    {
        Collection<ActionType> actions = getActions(selection);
        setCells(getTiles(actions));
    }

    private Collection<ActionType> getActions(Collection<Item> selection)
    {
        Collection<ActionType> result = selection.isEmpty() ? EnumSet.noneOf(ActionType.class) : EnumSet.allOf(ActionType.class);
        for (Item item: selection){
            Collection<ActionType> actions = getActions(item);
            result.retainAll(actions);
        }
        return result;
    }

    private Collection<ActionType> getActions(Item item)
    {
        if (item instanceof Unit){
            Unit unit = (Unit)item;
            return unit.getActions();
        }
        return Collections.emptyList();
    }

    private Collection<Item> getTiles(Collection<ActionType> actions)
    {
        Collection<Item> result = new ArrayList<Item>(actions.size());
        for (ActionType action: actions){
            result.add(getButton(action));
        }
        return result;
    }

    private ActionButton getButton(ActionType action)
    {
        ActionButton result = buttonProvider.get();
        result.setAction(action);
        result.setSize(54, 46);
        return result;
    }
}
