/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.flying.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.combatant.Combatant;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Air;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.projectile.ProjectileType.GryphonHammer;
import static com.evilbird.warcraft.object.unit.UnitType.GryphonRider;

/**
 * Instances of this factory create Gryphon Riders, Human heavy flying
 * {@link Combatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GryphonRiderFactory extends FlyingUnitFactory
{
    @Inject
    public GryphonRiderFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GryphonRiderFactory(AssetManager manager) {
        super(manager, GryphonRider);
    }

    @Override
    public FlyingUnit get(Identifier type) {
        FlyingUnit result = builder.build();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(0);
        result.setPiercingDamage(8);
        result.setBasicDamage(16);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GryphonRider", result));
        result.setMovementSpeed(8 * 14);
        result.setMovementCapability(Air);
        result.setSight(tiles(6));
        result.setType(GryphonRider);
        result.setProjectileType(GryphonHammer);
        return result;
    }
}