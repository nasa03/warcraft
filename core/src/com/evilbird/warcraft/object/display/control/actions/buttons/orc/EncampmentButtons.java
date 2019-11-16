/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.display.control.actions.buttons.orc;

import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.object.common.query.UnitOperations;
import com.evilbird.warcraft.object.data.player.Player;
import com.evilbird.warcraft.object.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.control.actions.buttons.BasicButtonController;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Collections;
import java.util.List;

import static com.evilbird.warcraft.object.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GruntButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.TrollAxethrowerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Grunt;
import static com.evilbird.warcraft.object.unit.UnitType.TrollAxethrower;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when an Orc Encampment (Barracks) is selected.
 *
 * @author Blair Butterworth
 */
public class EncampmentButtons extends BasicButtonController
{
    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (player.getLevel()) {
            case 1:
            case 2: return singletonList(GruntButton);
            case 3: return asList(GruntButton, TrollAxethrowerButton);
            default: return Collections.emptyList();
        }
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case GruntButton: return hasRequirements(player, Grunt);
            case TrollAxethrowerButton: return hasRequirements(player, TrollAxethrower, TrollLumberMill);
            default: return false;
        }
    }

    private boolean hasRequirements(Player player, UnitType type) {
        return hasResources(player, type);
    }

    private boolean hasRequirements(Player player, UnitType type, UnitType prerequisite) {
        return hasResources(player, type) && hasUnit(player, prerequisite);
    }
}