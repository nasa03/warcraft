/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.ranged.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.RangedSight;
import static com.evilbird.warcraft.object.unit.UnitType.ElvenRanger;

/**
 * Instances of this factory create Elven Rangers, an improved version of an
 * Elven Archer.
 *
 * @author Blair Butterworth
 */
public class ElvenRangerFactory extends ElvenArcherFactory
{
    @Inject
    public ElvenRangerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public ElvenRangerFactory(AssetManager manager) {
        super(manager);
    }

    @Override
    public RangedCombatant get(Identifier type) {
        RangedCombatant result = super.get(type);
        result.setHealth(50);
        result.setHealthMaximum(50);
        result.setIdentifier(objectIdentifier("ElvenRanger", result));
        result.setType(ElvenRanger);
        result.setSight(new UpgradeValue(RangedSight, tiles(5), tiles(9)));
        return result;
    }
}