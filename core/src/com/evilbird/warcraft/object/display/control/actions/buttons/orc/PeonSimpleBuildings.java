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

import java.util.List;

import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.EncampmentButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.ForgeButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.GreatHallButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.PigFarmButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.TrollLumberMillButton;
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.WatchTowerButton;
import static com.evilbird.warcraft.object.unit.UnitType.Encampment;
import static com.evilbird.warcraft.object.unit.UnitType.Forge;
import static com.evilbird.warcraft.object.unit.UnitType.GreatHall;
import static com.evilbird.warcraft.object.unit.UnitType.PigFarm;
import static com.evilbird.warcraft.object.unit.UnitType.TrollLumberMill;
import static com.evilbird.warcraft.object.unit.UnitType.WatchTower;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when a Peon is selected and the user navigates
 * to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class PeonSimpleBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                BuildCancelButton);

    private static final List<ActionButtonType> INTERMEDIATE_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                TrollLumberMillButton, BuildCancelButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PigFarmButton, EncampmentButton, GreatHallButton,
                TrollLumberMillButton, ForgeButton, WatchTowerButton,
                BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        return getButtons(player.getLevel());
    }

    private List<ActionButtonType> getButtons(int level) {
        if (level == 1) {
            return BASIC_BUTTONS;
        }
        if (level == 2 || level == 3) {
            return INTERMEDIATE_BUTTONS;
        }
        return ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);
        switch (button) {
            case BuildCancelButton: return true;
            case PigFarmButton: return hasResources(player, PigFarm);
            case EncampmentButton: return hasResources(player, Encampment);
            case TrollLumberMillButton: return hasResources(player, TrollLumberMill);
            case GreatHallButton: return hasResources(player, GreatHall);
            case ForgeButton: return hasResources(player, Forge);
            case WatchTowerButton: return hasResources(player, WatchTower);
            default: return false;
        }
    }
}
