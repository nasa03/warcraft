/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.GoldProduction;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.WoodProduction;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.common.value.FixedValue.Zero;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;

/**
 * <p>
 *   Instances of this factory create Peons, the land based gathering unit
 *   available to the Orc faction.
 * </p>
 * <p>
 *   The label of Peon denotes the lowest station amongst those in the Orcish
 *   Horde. Inferior in all skills of import, these dogs are relegated to
 *   menial tasks such as harvesting lumber and mining gold. Their labor is
 *   also required for the construction and maintenance of buildings necessary
 *   to support the vast undertakings of the Horde. Downtrodden, the Orc Peons
 *   slave thanklessly to please their overseers.
 * </p>
 *
 * @author Blair Butterworth
 */
public class PeonFactory extends GathererFactory
{
    @Inject
    public PeonFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PeonFactory(AssetManager manager) {
        super(manager, Peon);
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
        result.setAttackSpeed(1);
        result.setArmour(new UpgradeValue(MeleeDefence, 0, 2, 4));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 5, 7, 9));
        result.setPiercingDamage(1);
        result.setHealth(30);
        result.setHealthMaximum(30);
    }

    private void setGatheringAttributes(Gatherer result) {
        result.setGoldGatherSpeed(5);
        result.setGoldCapacity(new UpgradeValue(GoldProduction, 100, 110, 125));
        result.setWoodGatherSpeed(45);
        result.setWoodCapacity(new UpgradeValue(WoodProduction, 100, 110, 125));
        result.setOilGatherSpeed(0);
        result.setOilCapacity(Zero);
    }

    private void setIdentityAttributes(Gatherer result) {
        result.setIdentifier(objectIdentifier("Peon", result));
        result.setType(Peon);
    }

    private void setMovementAttributes(Gatherer result) {
        result.setMovementSpeed(8 * 10);
        result.setSight(tiles(4));
    }
}
