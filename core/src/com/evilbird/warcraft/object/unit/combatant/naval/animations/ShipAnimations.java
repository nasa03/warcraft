/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.naval.animations;

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
 * Defines a catalog of animations as laid out in naval unit texture atlas
 * files.
 *
 * @author Blair Butterworth
 */
public class ShipAnimations extends AnimationCatalog
{
    private static final GridPoint2 DECOMPOSE_SIZE = new GridPoint2(72, 72);

    public ShipAnimations(CombatantAssets assets) {
        this(assets.getBaseTexture(), assets.getDecomposeTexture(), assets.getSize());
    }

    public ShipAnimations(Texture base, Texture decompose, GridPoint2 size) {
        super(5);

        requireNonNull(base);
        requireNonNull(decompose);
        requireNonNull(size);

        idle(base, size);
        death(base, decompose, size);
    }

    private void idle(Texture base, GridPoint2 size) {
        alias(Attack, Idle);
        alias(Move, Idle);
        animation(Idle)
            .withTexture(base)
            .withSequence(0, 1)
            .withSize(size)
            .withInterval(10f)
            .looping();
    }

    private void death(Texture base, Texture decompose, GridPoint2 size) {
        sequence(Death)
            .element()
                .withTexture(base)
                .withSequence(size.y, 2)
                .withSize(size)
                .withInterval(0.5f)
            .element()
                .withTexture(decompose)
                .withSequence(432, 1)
                .withSize(DECOMPOSE_SIZE)
                .withInterval(2f);
    }
}
