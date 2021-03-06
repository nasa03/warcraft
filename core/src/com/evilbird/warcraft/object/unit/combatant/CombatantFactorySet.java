/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.combatant.flying.FlyingUnitFactorySet;
import com.evilbird.warcraft.object.unit.combatant.gatherer.GathererFactorySet;
import com.evilbird.warcraft.object.unit.combatant.melee.MeleeUnitFactorySet;
import com.evilbird.warcraft.object.unit.combatant.naval.NavalUnitFactorySet;
import com.evilbird.warcraft.object.unit.combatant.ranged.RangedUnitFactorySet;
import com.evilbird.warcraft.object.unit.combatant.siege.SiegeUnitFactorySet;
import com.evilbird.warcraft.object.unit.combatant.spellcaster.SpellCasterFactorySet;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Combatant Combatants}, a
 * {@link Unit} specialization that can move and attack other Units.
 *
 * @author Blair Butterworth
 */
public class CombatantFactorySet extends GameFactorySet<Combatant>
{
    @Inject
    public CombatantFactorySet(
        FlyingUnitFactorySet flyingUnitFactory,
        GathererFactorySet gathererFactory,
        MeleeUnitFactorySet meleeUnitFactory,
        NavalUnitFactorySet navalUnitFactory,
        RangedUnitFactorySet rangedUnitFactory,
        SiegeUnitFactorySet siegeUnitFactory,
        SpellCasterFactorySet spellCasterFactory)
    {
        addProvider(flyingUnitFactory);
        addProvider(gathererFactory);
        addProvider(meleeUnitFactory);
        addProvider(navalUnitFactory);
        addProvider(rangedUnitFactory);
        addProvider(siegeUnitFactory);
        addProvider(spellCasterFactory);
    }
}
