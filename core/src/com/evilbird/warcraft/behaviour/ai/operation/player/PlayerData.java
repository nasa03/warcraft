/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.player;

import com.evilbird.warcraft.behaviour.ai.order.OperationOrder;
import com.evilbird.warcraft.object.data.player.Player;

/**
 * A bundle of attributes required by player behaviours.
 *
 * @author Blair Butterworth
 */
public class PlayerData
{
    private Player player;
    private OperationOrder order;

    public PlayerData() {
    }

    public Player getPlayer() {
        return player;
    }

    public OperationOrder getOrder() {
        return order;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setOrder(OperationOrder order) {
        this.order = order;
    }
}
