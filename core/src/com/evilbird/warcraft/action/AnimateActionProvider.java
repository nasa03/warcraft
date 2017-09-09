package com.evilbird.warcraft.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.action.ModifyAction;
import com.evilbird.engine.action.duration.ActionDuration;
import com.evilbird.engine.action.duration.InstantDuration;
import com.evilbird.engine.action.modifier.ActionModifier;
import com.evilbird.engine.action.modifier.ConstantModifier;
import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemReferenceValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemComposite;
import com.evilbird.engine.item.specialized.AnimationIdentifier;
import com.evilbird.engine.item.specialized.AnimationProperty;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class AnimateActionProvider
{
    @Inject
    public AnimateActionProvider()
    {
    }

    public Action get(Item item, AnimationIdentifier animation)
    {
        ActionValue value = new ItemValue(item, AnimationProperty.Animation);
        return get(value, animation);
    }

    public Action get(ItemComposite itemParent, Identifier itemId, AnimationIdentifier animation)
    {
        ActionValue value = new ItemReferenceValue(itemParent, itemId, AnimationProperty.Animation);
        return get(value, animation);
    }

    public Action get(ActionValue value, AnimationIdentifier animation)
    {
        ActionModifier modifier = new ConstantModifier(animation);
        ActionDuration duration = new InstantDuration();
        return new ModifyAction(value, modifier, duration);
    }
}