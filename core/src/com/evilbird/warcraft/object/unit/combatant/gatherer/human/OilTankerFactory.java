/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Water;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.upgrade.UpgradeSeries.OilProduction;
import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;
import static com.evilbird.warcraft.object.unit.UnitType.OilTanker;

/**
 * <p>
 * Instances of this factory create Oil Tankers, an ocean based oil gathering
 * {@link Unit} available to the Human faction.
 * </p>
 * <p>
 * As the only ships which do not require oil be built, Oil Tankers make
 * possible the construction of the Alliance fleet. They are manned by hard
 * working, dependable mariners who search for the rich oil deposits which lie
 * beneath the waves. The crew of every Tanker is skilled in building Oil
 * Platforms and ferrying the oil back to a Shipyard or Oil Refinery where it
 * may be processed and put to use.
 * </p>
 *
 * @author Blair Butterworth
 */
public class OilTankerFactory extends GathererFactory
{
    @Inject
    public OilTankerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public OilTankerFactory(AssetManager manager) {
        super(manager, OilTanker);
    }

    @Override
    public Gatherer get(Identifier type) {
        Gatherer result = builder.build();
        setAttackAttributes(result);
        setGatheringAttributes(result);
        setIdentityAttributes(result);
        setMovementAttributes(result);
        return result;
    }

    private void setAttackAttributes(Gatherer result) {
        result.setArmour(0);
        result.setAttackSpeed(0);
        result.setBasicDamage(0);
        result.setPiercingDamage(0);
        result.setHealth(90);
        result.setHealthMaximum(90);
    }

    private void setGatheringAttributes(Gatherer result) {
        result.setGoldGatherSpeed(0);
        result.setGoldCapacity(Zero);
        result.setWoodGatherSpeed(0);
        result.setWoodCapacity(Zero);
        result.setOilGatherSpeed(5);
        result.setOilCapacity(new UpgradeValue(OilProduction, 100, 110, 125));
    }

    private void setIdentityAttributes(Gatherer result) {
        result.setIdentifier(objectIdentifier("OilTanker", result));
        result.setType(OilTanker);
    }

    private void setMovementAttributes(Gatherer result) {
        result.setMovementSpeed(8 * 10);
        result.setMovementCapability(Water);
        result.setSight(tiles(4));
    }
}