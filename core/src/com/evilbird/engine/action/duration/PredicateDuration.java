package com.evilbird.engine.action.duration;

import com.evilbird.engine.action.value.ActionValue;
import com.evilbird.engine.action.value.ItemValue;
import com.evilbird.engine.action.value.ObjectValue;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.item.Item;

import java.util.Objects;

public class PredicateDuration implements ActionDuration
{
    private ActionValue left;
    private ActionValue right;

    public PredicateDuration(Item item, Identifier property, Object value)
    {
        this(new ItemValue(item, property), new ObjectValue(value));
    }

    public PredicateDuration(ActionValue left, ActionValue right)
    {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean isComplete(float time)
    {
        Object leftValue = left.get();
        Object rightValue = right.get();
        return Objects.equals(leftValue, rightValue);
    }

    @Override
    public void restart()
    {
    }
}