/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.components.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnits;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.FortressButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.PeonButton;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Forge;
import static com.evilbird.warcraft.object.unit.UnitType.Fortress;
import static com.evilbird.warcraft.object.unit.UnitType.OgreMound;
import static com.evilbird.warcraft.object.unit.UnitType.Peon;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Orc Stronghold is selected.
 *
 * @author Blair Butterworth
 */
public class StrongholdButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(PeonButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PeonButton, FortressButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return player.getLevel() <= 10 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);

        if (button == PeonButton) {
            return hasResources(player, Peon);
        }
        if (button == FortressButton) {
            return hasResources(player, Fortress) && hasUnits(player, Encampment, Forge, OgreMound);
        }
        return false;
    }
}
