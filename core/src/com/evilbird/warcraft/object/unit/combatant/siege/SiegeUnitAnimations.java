/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.siege;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in siege unit texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class SiegeUnitAnimations extends AnimationCatalog
{
    public SiegeUnitAnimations(SiegeUnitAssets assets) {
        this(assets.getBaseTexture(), assets.getExplosionTexture(), assets.getSize());
    }

    public SiegeUnitAnimations(Texture base, Texture explosion, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(size);

        attack(base, size);
        death(explosion);
        idle(base, size);
        move(base, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y, 3)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void death(Texture explosion) {
        animation(Death)
            .withTexture(explosion)
            .withSequence(0, 16)
            .withBlankFrame()
            .withSize(64, 64)
            .withInterval(0.15f)
            .notLooping();
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(1f)
            .looping();
    }

    private void move(Texture base, GridPoint2 size) {
        animation(Move)
            .withTexture(base)
            .withSequence(0, 2)
            .withSize(size)
            .withInterval(0.15f)
            .looping();
    }
}
