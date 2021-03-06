/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit;

import com.evilbird.engine.common.lang.Identifier;

/**
 * Defines options of specifying unit sound varieties.
 *
 * @author Blair Butterworth
 */
public enum UnitSound implements Identifier
{
    Attack,
    Hit,
    Die,

    Selected,
    Acknowledge,
    Captured,
    Rescued,

    Placement,
    Build,
    Complete,
    Ready,

    ChopWood,
    DepositGold,
    DepositOil,
    DepositWood,

    Background
}
