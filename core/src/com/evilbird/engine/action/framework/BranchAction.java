package com.evilbird.engine.action.framework;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.evilbird.engine.common.function.Supplier;

public class BranchAction extends CompositeAction
{
    private Action delegate;
    private Supplier<Boolean> decision;

    public BranchAction(Supplier<Boolean> decision, Action trueAction, Action falseAction) {
        this.decision = decision;
        delegates.add(trueAction);
        delegates.add(falseAction);
    }

    @Override
    public boolean act(float delta) {
        if (delegate == null) {
            delegate = decision.get() == Boolean.TRUE ? delegates.get(0) : delegates.get(1);
        }
        return delegate.act(delta);
    }

    @Override
    public void restart() {
        super.restart();
        delegate = null;
    }
}