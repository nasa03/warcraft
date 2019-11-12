/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.object.interop;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.evilbird.engine.object.GameObject;

/**
 * Instances of this class decorate the LibGDX {@link Table} class with an
 * {@link GameObject}, allowing the Item to receive events from the Table without
 * inheriting from it.
 *
 * @author Blair Butterworth
 */
public class TableDecorator extends Table
{
    private GameObject gameObject;

    public TableDecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        gameObject.update(delta);
    }

    @Override
    public void draw(Batch batch, float alpha) {
        gameObject.draw(batch, alpha);
        super.draw(batch, alpha);
    }

    @Override
    public void positionChanged() {
        gameObject.positionChanged();
    }

    @Override
    public void sizeChanged() {
        gameObject.sizeChanged();
    }
}