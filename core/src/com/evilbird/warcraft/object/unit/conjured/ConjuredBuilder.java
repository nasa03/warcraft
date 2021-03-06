/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.unit.UnitAnimation;
import com.evilbird.warcraft.object.unit.UnitStyle;

import static com.evilbird.warcraft.object.unit.UnitZIndex.ConjuredIndex;

/**
 * Creates a new {@link ConjuredObject} instance whose visual and audible
 * presentation is defined by the given {@link ConjuredAssets}.
 *
 * @author Blair Butterworth
 */
public abstract class ConjuredBuilder
{
    private ConjuredAssets assets;
    private AnimationCatalog animations;
    private SoundCatalog sounds;

    public ConjuredBuilder(ConjuredAssets assets) {
        this.assets = assets;
    }

    public ConjuredObject build() {
        ConjuredObject result = newObject(getSkin());
        result.setAnimation(UnitAnimation.Idle);
        result.setHealth(1);
        result.setHealthMaximum(1);
        result.setSelected(false);
        result.setSelectable(false);
        result.setSight(0);
        result.setTouchable(Touchable.disabled);
        result.setZIndex(ConjuredIndex);
        return result;
    }

    protected abstract ConjuredObject newObject(Skin skin);

    protected Skin getSkin() {
        UnitStyle style = getStyle();
        Skin skin = new Skin();
        skin.add("default", style, AnimatedObjectStyle.class);
        skin.add("default", style, UnitStyle.class);
        return skin;
    }

    protected UnitStyle getStyle() {
        SoundCatalog sounds = getSounds();
        AnimationCatalog animations = getAnimations();

        UnitStyle style = new UnitStyle();
        style.animations = animations.get();
        style.sounds = sounds.get();
        return style;
    }

    protected AnimationCatalog getAnimations() {
        if (animations == null) {
            animations = newAnimations(assets);
        }
        return animations;
    }

    protected abstract AnimationCatalog newAnimations(ConjuredAssets assets);

    protected SoundCatalog getSounds() {
        if (sounds == null) {
            sounds = newSounds(assets);
        }
        return sounds;
    }

    protected abstract SoundCatalog newSounds(ConjuredAssets assets);
}
