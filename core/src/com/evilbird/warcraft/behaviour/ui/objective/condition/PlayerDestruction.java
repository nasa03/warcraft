/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.objective.condition;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.warcraft.action.death.RemoveEvent;

import java.util.function.Predicate;

import static com.evilbird.engine.object.utility.GameObjectOperations.hasNone;
import static com.evilbird.warcraft.object.common.query.UnitPredicates.isAlive;

/**
 * Represents a {@link ScenarioCondition} that is fulfilled when all of the
 * users units have been destroyed.
 *
 * @author Blair Butterworth
 */
public class PlayerDestruction extends PlayerCondition
{
    private Predicate<GameObject> livingUnits;

    /**
     * Constructs a new instance of this class given a player identifier.
     *
     * @param player an {@link Identifier}.
     *
     * @throws NullPointerException if the given player identifier is
     *                              {@code null}.
     */
    public PlayerDestruction(Identifier player) {
        super(player);
        livingUnits = isAlive();
    }

    /**
     * Creates a new PlayerDestruction that will be fulfilled when all of the
     * units belonging to the player with the given {@link Identifier} have
     * been destroyed.
     *
     * @param player    an {@link Identifier}.
     * @return          a new PlayerDestruction instance.
     *
     * @throws NullPointerException if the given player identifier is
     *                              {@code null}.
     */
    public static PlayerDestruction playerDestroyed(Identifier player) {
        return new PlayerDestruction(player);
    }

    /**
     * Creates a new PlayerDestruction that will be fulfilled when all of the
     * units belonging to the player with the given {@link Identifier} have
     * been captured. Functionally this is the same as destruction, but with a
     * nomenclature that tries to avoid confusion.
     *
     * @param player    an {@link Identifier}.
     * @return          a new PlayerDestruction instance.
     *
     * @throws NullPointerException if the given player identifier is
     *                              {@code null}.
     */
    public static PlayerDestruction playerCaptured(Identifier player) {
        return new PlayerDestruction(player);
    }

    @Override
    protected boolean applicable(EventQueue queue) {
        return queue.hasEvents(RemoveEvent.class);
    }

    @Override
    protected boolean evaluate(GameObjectContainer state) {
        return hasNone(player, livingUnits);
    }
}
