/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.select;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.evilbird.engine.action.common.AudibleAction;
import com.evilbird.engine.action.common.SelectAction;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.action.framework.ParallelAction;
import com.evilbird.engine.common.lang.Selectable;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.Audible;
import com.evilbird.warcraft.item.unit.UnitSound;

import javax.inject.Inject;

public class SelectToggleAction extends DelegateAction
{
    private boolean notified;
    private SelectObserver observer;
    private SelectAction select;
    private AudibleAction audio;

    @Inject
    public SelectToggleAction() {
        select = new SelectAction();
        audio = new AudibleAction();
        delegate = new ParallelAction(select, audio);
    }

    @Override
    public boolean act(float delta) {
        notifySelected();
        return delegate.act(delta);
    }

    public void setItem(Item item) {
        boolean selected = !item.getSelected();
        select.setSelected(selected);
        select.setSelectable(item);
        audio.setSound(selected ? UnitSound.Selected : null);
        audio.setAudible((Audible)item);
    }

    public void setObserver(SelectObserver observer) {
        this.observer = observer;
    }

    @Override
    public void reset() {
        super.reset();
        notified = false;
    }

    private void notifySelected() {
        if (!notified && observer != null) {
            notified = true;
            observer.onSelect((Item)select.getSelectable(), select.getSelected());
        }
    }
}
