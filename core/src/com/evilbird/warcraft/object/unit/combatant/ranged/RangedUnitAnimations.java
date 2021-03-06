/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.ranged;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.Attack;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Death;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Move;
import static java.util.Objects.requireNonNull;

/**
 * Defines a catalog of animations as laid out in ranged unit texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class RangedUnitAnimations extends AnimationCatalog
{
    private static final GridPoint2 SIZE = new GridPoint2(72, 72);

    public RangedUnitAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getDecomposeTexture());
    }

    public RangedUnitAnimations(Texture general, Texture decompose) {
        this(general, decompose, SIZE);
    }

    public RangedUnitAnimations(Texture base, Texture decompose, GridPoint2 size) {
        super(4);

        requireNonNull(base);
        requireNonNull(decompose);
        requireNonNull(size);

        attack(base, size);
        idle(base, size);
        move(base, size);
        death(base, decompose, size);
    }

    private void attack(Texture base, GridPoint2 size) {
        animation(Attack)
            .withTexture(base)
            .withSequence(size.y * 5, 2)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(0.15f)
            .notLooping();
    }

    private void idle(Texture base, GridPoint2 size) {
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void move(Texture base, GridPoint2 size) {
        animation(Move)
            .withTexture(base)
            .withSequence(0, 5)
            .withSize(size)
            .withInterval(0.10f)
            .looping();
    }

    private void death(Texture base, Texture decompose, GridPoint2 size) {
        sequence(Death)
            .element()
                .withTexture(base)
                .withSequence(size.y * 9, 3)
                .withSize(size)
                .withInterval(0.15f)
            .element()
                .withTexture(decompose)
                .withSequence(0, 6)
                .withSize(size)
                .withInterval(5f);
    }
}
