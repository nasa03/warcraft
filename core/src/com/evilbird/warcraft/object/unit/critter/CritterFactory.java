/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.critter;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.critter.neutral.BoarFactory;
import com.evilbird.warcraft.object.unit.critter.neutral.SealFactory;
import com.evilbird.warcraft.object.unit.critter.neutral.SheepFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Critter Critters}, animals that
 * inhabit the game world.
 *
 * @author Blair Butterworth
 */
public class CritterFactory extends GameFactorySet<Critter>
{
    @Inject
    public CritterFactory(BoarFactory boarFactory, SealFactory sealFactory, SheepFactory sheepFactory) {
        addProvider(UnitType.Boar, boarFactory);
        addProvider(UnitType.Seal, sealFactory);
        addProvider(UnitType.Sheep, sheepFactory);
    }
}