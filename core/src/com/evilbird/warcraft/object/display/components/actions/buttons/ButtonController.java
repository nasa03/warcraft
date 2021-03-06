/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.display.components.actions.ActionButton;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.ActionPane;

import java.util.List;

/**
 * Implementors of this interface control the layout and enablement of
 * {@link ActionButton buttons} displayed in an {@link ActionPane}.
 *
 * @author Blair Butterworth
 */
public interface ButtonController
{
    /**
     * Returns the buttons that should be shown for the given {@link GameObject}.
     * Buttons will be displayed in the returned order.
     *
     * @param gameObject  the selected {@link GameObject}, whose attributes will be used
     *              to determine which buttons should be displayed.
     *
     * @return an ordered {@link List} of {@link ActionButtonType buttons}.
     */
    List<ActionButtonType> getButtons(GameObject gameObject);

    /**
     * Determines if buttons of the given type should be enabled or not.
     *
     * @param button    the {@link ActionButtonType type} of the button to test.
     * @param gameObject      the selected {@link GameObject}, whose attributes will be
     *                  used to determine if the button should be enabled.
     *
     * @return {@code true} if the button should be enabled.
     */
    boolean getEnabled(ActionButtonType button, GameObject gameObject);
}
