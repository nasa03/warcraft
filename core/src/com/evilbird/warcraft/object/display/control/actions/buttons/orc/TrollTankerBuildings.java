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
import static com.evilbird.warcraft.object.display.control.actions.ActionButtonType.OilRigButton;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;
import static java.util.Arrays.asList;

/**
 * Controls the buttons shown when an Orc Troll Tanker (Oil Tanker) is selected
 * and the user navigates to the simple building menu.
 *
 * @author Blair Butterworth
 */
public class TrollTankerBuildings extends BasicButtonController
{
    private static final List<ActionButtonType> BUTTONS =
            asList(OilRigButton, BuildCancelButton);

    @Override
    public List<ActionButtonType> getButtons(GameObject gameObject) {
        return BUTTONS;
    }

    @Override
    public boolean getEnabled(ActionButtonType button, GameObject gameObject) {
        Player player = UnitOperations.getPlayer(gameObject);

        if (button == BuildCancelButton) {
            return true;
        }
        if (button == OilRigButton) {
            return hasResources(player, OilRig);
        }
        return false;
    }
}
