/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.behaviour.scenario.condition;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.events.Event;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.ItemRoot;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.action.common.remove.RemoveEvent;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.unit.combatant.Combatant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.item.data.player.PlayerIds.Player1;
import static com.evilbird.warcraft.item.data.player.PlayerIds.Player2;
import static com.evilbird.warcraft.item.unit.UnitType.ElvenArcher;
import static com.evilbird.warcraft.item.unit.UnitType.Footman;
import static org.junit.Assert.*;

/**
 * Instances of this unit test validate the {@link UnitDestruction} class.
 *
 * @author Blair Butterworth
 */
public class UnitDestructionTest extends GameTestCase
{
    private UnitDestruction condition;
    private ItemRoot root;
    private Player player1;
    private Player player2;
    private Combatant combatant1;
    private Combatant combatant2;
    private Combatant combatant3;

    @Before
    public void setup() {
        root = TestItemRoots.newTestRoot("root");
        player1 = TestPlayers.newTestPlayer(Player1, root);
        player2 = TestPlayers.newTestPlayer(Player2, root);

        root.clearItems();
        root.addItem(player1);
        root.addItem(player2);

        combatant1 = TestCombatants.newTestCombatant(new TextIdentifier("test1"), Footman, root, player1);
        combatant2 = TestCombatants.newTestCombatant(new TextIdentifier("test2"), Footman, root, player1);
        combatant3 = TestCombatants.newTestCombatant(new TextIdentifier("test3"), Footman, root, player2);

        player1.clearItems();
        player2.clearItems();
        player1.addItem(combatant1);
        player1.addItem(combatant2);
        player2.addItem(combatant3);

        condition = new UnitDestruction(Player1, Footman);
        condition.initialize(root);
    }

    @Test
    public void applicableTest() {
        Item subject = TestCombatants.newTestCombatant("footman");
        Event event = new RemoveEvent(subject);

        EventQueue queue = new EventQueue();
        queue.add(event);

        Assert.assertTrue(condition.applicable(queue));
    }

    @Test
    public void notApplicableTest() {
        EventQueue queue = new EventQueue();
        Assert.assertFalse(condition.applicable(queue));
    }

    @Test
    public void evaluateTrueTest() {
        combatant1.setType(ElvenArcher);
        combatant2.setType(ElvenArcher);
        combatant3.setType(Footman);
        Assert.assertTrue(condition.evaluate(root));
    }

    @Test
    public void evaluateFalseTest() {
        combatant1.setType(ElvenArcher);
        combatant2.setType(Footman);
        combatant3.setType(ElvenArcher);
        Assert.assertFalse(condition.evaluate(root));
    }

    @Test
    public void evaluateEmptyTest() {
        player1.removeItem(combatant1);
        player1.removeItem(combatant2);
        Assert.assertTrue(condition.evaluate(root));
    }
}