/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.buff;

import com.evilbird.engine.item.ItemFactory;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.common.value.ValueProperty;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import com.evilbird.warcraft.item.unit.gatherer.Gatherer;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collection;

/**
 * A spell that increases the speed for a given targets movement and attack.
 * The spell is cast instantaneously and wears off after a period of time.
 *
 * @author Blair Butterworth
 */
public class HasteSpell extends BuffSpellAction
{
    @Inject
    public HasteSpell(ItemFactory factory, HasteCancel cancel) {
        super(Spell.Haste, EffectType.Spell, factory, cancel);
    }

    @Override
    protected Collection<ValueProperty> buffedProperties(Combatant target) {
        Gatherer gatherer = (Gatherer)target;
        return Arrays.asList(
            gatherer.getAttackSpeedProperty(),
            gatherer.getMovementSpeedProperty(),
            gatherer.getGoldGatherSpeedProperty(),
            gatherer.getOilGatherSpeedProperty(),
            gatherer.getWoodGatherSpeedProperty());
    }
}