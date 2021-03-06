/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Disposable;
import com.evilbird.engine.common.graphics.DrawableUtils;
import com.evilbird.engine.common.graphics.TextureUtils;

/**
 * <p>
 * Represents a simple textual button control.
 * </p>
 * <p>
 *  This control also overrides the default {@link TextButton} behaviour,
 *  allowing the colour of the controls border to be set.
 * </p>
 *
 * @author Blair Butterworth
 */
public class LabelButton extends TextButton implements Disposable
{
    private Skin skin;
    private LabelButtonStyle style;
    private Color borderColour;
    private Drawable borderDrawable;
    private Texture borderTexture;
    private boolean inFocus;

    public LabelButton(String text, Skin skin) {
        this(text, skin, "default");
    }

    public LabelButton(String text, Skin skin, String style) {
        super(text, skin, style);
        this.skin = skin;
        addListener(new ClickObserver());
        addListener(new FocusObserver());
    }

    public void addSelectListener(SelectListener listener) {
        addListener(new SelectListenerAdapter(listener));
    }

    @Override
    public void dispose() {
        if (borderTexture != null) {
            borderTexture.dispose();
            borderDrawable = null;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if (borderDrawable != null) {
            borderDrawable.draw(batch, getX(), getY(), getWidth(), getHeight());
        }
    }

    public void onClick() {
        if (style != null && style.clickSound != null) {
            //style.clickSound.play();
        }
    }

    public void onFocus(boolean focused) {
        inFocus = focused;
        updateBorderColour();
    }

    public void setStyle(String name) {
        setStyle(skin.get(name, LabelButtonStyle.class));
    }

    @Override
    public void setStyle(ButtonStyle buttonStyle) {
        super.setStyle(buttonStyle);
        if (buttonStyle instanceof LabelButtonStyle) {
            style = (LabelButtonStyle)buttonStyle;
            updateBorderColour();
        }
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        updateBorder();
    }

    private void updateBorder() {
        dispose();
        if (borderColour != null) {
            borderTexture = TextureUtils.getRectangle((int)getWidth(), (int)getHeight(), borderColour);
            borderDrawable = DrawableUtils.getDrawable(borderTexture);
        }
    }

    private void updateBorderColour() {
        if (style != null) {
            borderColour = inFocus ? style.borderColourFocused : style.borderColour;
            updateBorder();
        }
    }

    private class FocusObserver extends FocusListener {
        @Override
        public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
            onFocus(focused);
        }
    }

    private class ClickObserver extends ChangeListener {
        @Override
        public void changed(ChangeEvent event, Actor actor) {
            onClick();
        }
    }
}
