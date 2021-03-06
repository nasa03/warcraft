/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu.outro;

import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameEngine;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.state.State;
import com.evilbird.test.data.item.TestItemRoots;
import com.evilbird.test.data.item.TestPlayers;
import com.evilbird.test.data.item.TestSkins;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.data.player.PlayerType;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.RETURNS_MOCKS;

/**
 * Instances of this unit test validate logic in the {@link OutroMenu}
 * class.
 *
 * @author Blair Butterworth
 */
public class OutroMenuTest extends GameTestCase
{
    private OutroMenu menu;

    @Before
    public void setup() {
        OutroMenuStrings bundle = Mockito.mock(OutroMenuStrings.class, RETURNS_MOCKS);
        menu = new OutroMenu(Mockito.mock(DeviceDisplay.class), TestSkins.newOutroMenuSkin());
        menu.setLabelBundle(bundle);
    }

    @Test
    public void setControllerTest() {
        GameObjectContainer world = TestItemRoots.newTestRoot("world");

        Player humanPlayer = TestPlayers.newTestPlayer(new TextIdentifier("human"), world);
        humanPlayer.setType(PlayerType.Corporeal);
        world.addObject(humanPlayer);

        Player aiPlayer = TestPlayers.newTestPlayer(new TextIdentifier("ai"), world);
        aiPlayer.setType(PlayerType.Artificial);
        world.addObject(aiPlayer);

        State state = Mockito.mock(State.class);
        Mockito.when(state.getWorld()).thenReturn(world);

        GameEngine engine = Mockito.mock(GameEngine.class);
        Mockito.when(engine.getState()).thenReturn(state);

        menu.setController(engine);
    }
}
