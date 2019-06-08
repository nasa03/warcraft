/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit;

import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.item.ItemType;

/**
 * Instances of this class define identifiers for items.
 *
 * @author Blair Butterworth
 */
@SerializedType("Units")
public enum UnitType implements ItemType
{
    /* Building - Human */
    Barracks,
    Farm,
    LumberMill,
    TownHall,

    /* Building - Orc */
    WatchTower,

    /* Building - Neutral */
    CircleOfPower,

    /* Combatant - Human */
    Footman,
    ElvenArcher,
    ElvenDestroyer,

    /* Combatant - Orc */
    Grunt,
    TrollAxethrower,
    TrollDestroyer,

    /* Combatant - Neutral */
    Seal,

    /* Gatherer */
    Peasant,
    Peon,

    /* Resource */
    GoldMine,
    OilPatch
}