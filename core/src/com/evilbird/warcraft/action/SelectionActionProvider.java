package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.ParallelAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemProperties;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class SelectionActionProvider implements ActionProvider
{
    private AudioActionProvider audioActionProvider;

    @Inject
    public SelectionActionProvider(AudioActionProvider audioActionProvider)
    {
        this.audioActionProvider = audioActionProvider;
    }

    @Override
    public Action get(ActionType action, Item item, Item target, UserInput input)
    {
        return get(item);
    }

    public Action get(Item item)
    {
        return get(item, !item.getSelected());
    }

    public Action get(Item item, boolean selected)
    {
        Action result = newSelectionAction(item, selected);
        if (selected)
        {
            Action sound = audioActionProvider.get(item, UnitSound.Selected);
            result = new ParallelAction(result, sound);
        }
        return result;
    }

    private Action newSelectionAction(Item item, boolean selected)
    {
        ActionValue value = new ItemValue(item, ItemProperties.Selected);
        ActionModifier modifier = new ConstantModifier(selected);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
}