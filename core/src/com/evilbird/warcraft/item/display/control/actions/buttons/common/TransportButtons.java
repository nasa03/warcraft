/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.common;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.ButtonController;
import com.evilbird.warcraft.item.unit.Unit;

import java.util.List;

import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.DisembarkButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.MoveButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.StopButton;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a transport is selected.
 *
 * @author Blair Butterworth
 */
public class TransportButtons implements ButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return asList(MoveButton, StopButton, DisembarkButton);
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        if (button == DisembarkButton) {
            Unit unit = (Unit) gameObject;
            return unit.hasAssociatedItems();
        }
        return true;
    }
}
