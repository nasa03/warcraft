/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.spell.creature;

import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.warcraft.action.common.create.CreateEvents;
import com.evilbird.warcraft.action.spell.SpellAction;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.common.spell.Spell;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.effect.EffectType;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

/**
 * A spell that creates a conjured creature.
 *
 * @author Blair Butterworth
 */
public class CreatureSpellAction extends SpellAction
{
    private UnitType creatureType;
    private CreateEvents createEvents;
    private CreatureSpellCancel cancelAction;

    public CreatureSpellAction(
        Spell spell,
        EffectType effect,
        UnitType creature,
        GameObjectFactory factory,
        CreateEvents createEvents,
        CreatureSpellCancel cancelAction)
    {
        super(spell, effect, factory);
        this.creatureType = creature;
        this.createEvents = createEvents;
        this.cancelAction = cancelAction;
    }

    @Override
    protected void initialize() {
        super.initialize();
        addCreature();
    }

    protected Combatant addCreature() {
        GameObject caster = getSubject();
        Player player = UnitOperations.getPlayer(caster);

        Combatant creature = (Combatant)factory.get(creatureType);

        if (cancelAction != null) {
            cancelAction.setItem(caster);
            cancelAction.setTarget(creature);
            creature.addAction(cancelAction, spell.getEffectDuration());
        }

        player.addObject(creature);
        createEvents.notifyCreate(creature);

        return creature;
    }
}
