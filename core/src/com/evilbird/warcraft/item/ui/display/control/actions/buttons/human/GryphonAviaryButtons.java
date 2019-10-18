/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.ui.display.control.actions.buttons.human;

import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.common.query.UnitOperations;
import com.evilbird.warcraft.item.data.player.Player;
import com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType;
import com.evilbird.warcraft.item.ui.display.control.actions.buttons.BasicButtonController;

import java.util.List;

import static com.evilbird.warcraft.item.ui.display.control.actions.ActionButtonType.TrainGryphonRiderButton;
import static com.evilbird.warcraft.item.unit.UnitType.GryphonRider;
import static java.util.Collections.singletonList;

/**
 * Controls the buttons shown when a Human Gryphon Aviary is selected.
 *
 * @author Blair Butterworth
 */
public class GryphonAviaryButtons extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS = singletonList(TrainGryphonRiderButton);

    @Override
    public List<ActionButtonType> getButtons(Item item) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, Item item) {
        Player player = UnitOperations.getPlayer(item);
        return hasResources(player, GryphonRider);
    }
}
