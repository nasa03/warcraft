/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.projectile;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.object.AnimatedObject;
import com.evilbird.engine.object.AnimatedObjectStyle;
import com.evilbird.warcraft.object.common.capability.MovableObject;
import com.evilbird.warcraft.object.common.capability.TerrainType;

/**
 * Represents an object that can be propelled towards an enemy to cause damage.
 *
 * @author Blair Butterworth
 */
public class Projectile extends AnimatedObject implements MovableObject
{
    private static final transient int MOVEMENT_SPEED = 500;
    private static final transient TerrainType MOVEMENT_CAPABILITY = TerrainType.Air;

    /**
     * Constructs a new instance of this class given a {@link Skin} describing
     * its visual and auditory presentation.
     *
     * @param skin  a {@link Skin} instance containing, amongst others, a
     *              {@link AnimatedObjectStyle}.
     */
    public Projectile(Skin skin) {
        super(skin);
    }

    @Override
    public int getMovementSpeed() {
        return MOVEMENT_SPEED;
    }

    @Override
    public TerrainType getMovementCapability() {
        return MOVEMENT_CAPABILITY;
    }
}
