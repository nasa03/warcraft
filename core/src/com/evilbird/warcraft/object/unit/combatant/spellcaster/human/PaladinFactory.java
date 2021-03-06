/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant.spellcaster.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.common.value.UpgradeValue;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCaster;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCasterFactory;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDamage;
import static com.evilbird.warcraft.data.upgrade.UpgradeSeries.MeleeDefence;
import static com.evilbird.warcraft.object.common.capability.TerrainType.Land;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Knight;
import static com.evilbird.warcraft.object.unit.UnitType.Paladin;

/**
 * Instances of this factory create Paladins, knights with additional attack
 * damage and sight.
 *
 * @author Blair Butterworth
 */
public class PaladinFactory extends SpellCasterFactory
{
    @Inject
    public PaladinFactory(Device device) {
        this(device.getAssetStorage());
    }

    public PaladinFactory(AssetManager manager) {
        super(manager, Knight, Paladin);
    }

    @Override
    public SpellCaster get(Identifier type) {
        SpellCaster result = builder.build();
        result.setAttackSpeed(1f);
        result.setArmour(new UpgradeValue(MeleeDefence, 4, 6, 8));
        result.setBasicDamage(new UpgradeValue(MeleeDamage, 10, 12, 14));
        result.setPiercingDamage(2);
        result.setHealth(90f);
        result.setHealthMaximum(90f);
        result.setIdentifier(objectIdentifier("Paladin", result));
        result.setMana(100f);
        result.setManaMaximum(255f);
        result.setManaRegeneration(1f);
        result.setMovementSpeed(8 * 13);
        result.setMovementCapability(Land);
        result.setSight(tiles(4));
        result.setType(Paladin);
        return result;
    }
}