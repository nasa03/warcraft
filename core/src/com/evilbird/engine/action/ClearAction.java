package com.evilbird.engine.action;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.item.Item;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class ClearAction extends Action
{
    private Item item;

    public ClearAction(Item item)
    {
        this.item = item;
    }

    @Override
    public boolean act(float delta)
    {
        item.clearActions();
        return true;
    }
}