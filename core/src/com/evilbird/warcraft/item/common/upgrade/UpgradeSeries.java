/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.common.upgrade;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.MeleeType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.OilProduction2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedAccuracy1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.NavalDefence2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedSight1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedType1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.RangedWeapon1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.SiegeDamage2;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction1;
import static com.evilbird.warcraft.item.common.upgrade.Upgrade.WoodProduction2;

/**
 * Defines a grouping of {@link Upgrade upgrades}, where each
 * {@link UpgradeRank rank} builds upon the previous to improve a game objects
 * attributes.
 *
 * @author Blair Butterworth
 */
public enum UpgradeSeries
{
    None,
    MeleeDamage,
    MeleeDefence,
    MeleeType,
    RangedAccuracy,
    RangedDamage,
    RangedSight,
    RangedWeapon,
    RangedType,
    NavalDamage,
    SiegeDamage,
    NavalDefence,
    GoldProduction,
    OilProduction,
    WoodProduction;

    /**
     * Returns the {@link Collection} of {@link Upgrade Upgrades} in the
     * {@code UpgradeSeries}.
     *
     * @return an unmodifiable {@code Collection} of {@code Upgrade Upgrades}.
     */
    public List<Upgrade> getUpgrades() {
        switch (this) {
            case None: return Collections.emptyList();
            case MeleeDamage: return Arrays.asList(MeleeDamage1, MeleeDamage2);
            case MeleeType: return Collections.singletonList(MeleeType1);
            case RangedDamage: return Arrays.asList(RangedDamage1, RangedDamage2);
            case RangedAccuracy: return Collections.singletonList(RangedAccuracy1);
            case RangedType: return Collections.singletonList(RangedType1);
            case RangedSight: return Collections.singletonList(RangedSight1);
            case RangedWeapon: return Collections.singletonList(RangedWeapon1);
            case NavalDamage: return Arrays.asList(NavalDamage1, NavalDamage2);
            case SiegeDamage: return Arrays.asList(SiegeDamage1, SiegeDamage2);
            case MeleeDefence: return Arrays.asList(MeleeDefence1, MeleeDefence2);
            case NavalDefence: return Arrays.asList(NavalDefence1, NavalDefence2);
            case GoldProduction : return Arrays.asList(GoldProduction1, GoldProduction2);
            case OilProduction: return Arrays.asList(OilProduction1, OilProduction2);
            case WoodProduction: return Arrays.asList(WoodProduction1, WoodProduction2);
            default: throw new UnsupportedOperationException(this.toString());
        }
    }
}
