/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.combatant.CombatantFactoryBase;
import com.evilbird.warcraft.item.unit.combatant.RangedCombatant;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.WarcraftItemConstants.tiles;
import static com.evilbird.warcraft.item.common.movement.MovementCapability.Water;
import static com.evilbird.warcraft.item.projectile.ProjectileType.Cannon;
import static com.evilbird.warcraft.item.unit.UnitType.GnomishSubmarine;

/**
 * Instances of this factory create Gnomish Submarines, Human underwater
 * seaborne {@link RangedCombatant Combatants}.
 *
 * @author Blair Butterworth
 */
public class GnomishSubmarineFactory extends CombatantFactoryBase
{
    @Inject
    public GnomishSubmarineFactory(Device device) {
        this(device.getAssetStorage());
    }

    public GnomishSubmarineFactory(AssetManager manager) {
        super(manager, GnomishSubmarine);
    }

    @Override
    public Combatant get(Identifier type) {
        RangedCombatant result = builder.newSeaCombatant();
        result.setAttackSpeed(1.5f);
        result.setAttackRange(tiles(4));
        result.setArmour(10);
        result.setPiercingDamage(2);
        result.setBasicDamage(35);
        result.setHealth(100);
        result.setHealthMaximum(100);
        result.setIdentifier(objectIdentifier("GnomishSubmarine", result));
        result.setName("Gnomish Submarine");
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setSight(tiles(8));
        result.setType(GnomishSubmarine);
        result.setProjectileType(Cannon);
        return result;
    }
}