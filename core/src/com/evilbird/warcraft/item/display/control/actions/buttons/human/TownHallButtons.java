/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.common.query.UnitOperations.hasUnit;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.KeepButton;
import static com.evilbird.warcraft.item.display.control.actions.ActionButtonType.PeasantButton;
import static com.evilbird.warcraft.item.unit.UnitType.Barracks;
import static com.evilbird.warcraft.item.unit.UnitType.Keep;
import static com.evilbird.warcraft.item.unit.UnitType.Peasant;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Town Hall is selected.
 *
 * @author Blair Butterworth
 */
public class TownHallButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BASIC_BUTTONS =
        singletonList(PeasantButton);

    private static final List<ActionButtonType> ADVANCED_BUTTONS =
        asList(PeasantButton, KeepButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        Player player = UnitOperations.getPlayer(item);
        return player.getLevel() <= 5 ? BASIC_BUTTONS : ADVANCED_BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        switch (button) {
            case PeasantButton: return hasResources(player, Peasant);
            case KeepButton: return hasResources(player, Keep) && hasUnit(player, Barracks);
            default: return false;
        }
    }
}