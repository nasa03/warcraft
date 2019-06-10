/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.projectile;

import com.evilbird.engine.item.specialized.AnimatedItem;

import javax.inject.Inject;

/**
 * Represents an object that can be propelled towards an enemy to cause damage.
 *
 * @author Blair Butterworth
 */
public class Projectile extends AnimatedItem
{
    @Inject
    public Projectile() {
    }
}