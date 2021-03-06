/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.selection;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.evilbird.engine.action.Action;
import com.evilbird.engine.device.UserInput;
import com.evilbird.engine.device.UserInputType;
import com.evilbird.engine.events.EventQueue;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectContainerType;
import com.evilbird.test.testcase.ActionTestCase;
import com.evilbird.warcraft.action.selector.SelectorArea;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static com.evilbird.engine.object.utility.GameObjectPredicates.withType;
import static com.evilbird.warcraft.action.selector.SelectorActions.ResizeAreaSelector;
import static com.evilbird.warcraft.action.selector.SelectorActions.ShowAreaSelector;
import static com.evilbird.warcraft.object.selector.SelectorType.AreaSelector;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Instances of this unit test validate the {@link SelectorArea} class.
 *
 * @author Blair Butterworth
 */
public class SelectorAreaTest extends ActionTestCase
{
    @Before
    @Override
    public void setup() {
        super.setup();
        GameObjectContainer container = gameObject.getRoot();
        container.setIdentifier(GameObjectContainerType.World);
    }

    @Override
    protected Action newAction() {
        SelectorArea action = new SelectorArea(Mockito.mock(EventQueue.class));
        action.setIdentifier(ShowAreaSelector);
        return action;
    }

    @Override
    protected Enum newIdentifier() {
        return ShowAreaSelector;
    }

    @Test
    public void beginTest() {
        GameObject box = getSelectionBox();
        assertNull(box);

        action.setIdentifier(ShowAreaSelector);
        assertTrue(action.run(1));

        box = getSelectionBox();
        assertNotNull(box);
    }

    private int toScreen(int position) {
        return Gdx.graphics.getHeight() - 1 - position;
    }

    @Test
    public void updateTest() {
        Vector2 input1 = new Vector2(20, toScreen(20));
        Vector2 input2 = new Vector2(50, toScreen(50));

        action.setIdentifier(ResizeAreaSelector);
        action.setCause(new UserInput(UserInputType.PressDrag, input2, input1, 1));

        assertTrue(action.run(1));
        GameObject box = getSelectionBox();

        assertEquals(new Vector2(20, 20), box.getPosition());
        assertEquals(new Vector2(30, 30), box.getSize());
    }

//    @Test
//    public void removeTest() {
//        Unit unit = (Unit)gameObject;
//        unit.setPosition(30, 30);
//        unit.setSize(10, 10);
//        unit.setSelected(false);
//        unit.setSelectable(true);
//        unit.setTouchable(Touchable.enabled);
//        gameObject.getRoot().addObject(gameObject);
//
//        Vector2 input1 = new Vector2(35, toScreen(35));
//        Vector2 input2 = new Vector2(50, toScreen(50));
//
//        action.setIdentifier(SelectorActions.HideAreaSelector);
//        action.setCause(new UserInput(UserInputType.PressUp, input1, input2, 1));
//
//        assertTrue(action.act(1));
//        assertTrue(unit.getSelected());
//    }

    private GameObject getSelectionBox() {
        GameObjectContainer root = gameObject.getRoot();
        return root.find(withType(AreaSelector));
    }
}