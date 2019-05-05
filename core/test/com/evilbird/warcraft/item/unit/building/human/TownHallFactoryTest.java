/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.item.Item;
import com.evilbird.test.testcase.FactoryTestCase;
import com.evilbird.warcraft.item.unit.UnitType;

import java.util.Map;

/**
 * Instances of this unit test validate logic in the {@link TownHallFactory} class.
 *
 * @author Blair Butterworth
 */
public class TownHallFactoryTest extends FactoryTestCase<Item>
{
    @Override
    protected AssetProvider<Item> newFactory(AssetManager assets) {
        return new TownHallFactory(assets);
    }

    @Override
    protected Map<String, Object> newValueProperties() {
        return Maps.of("type", UnitType.TownHall,
                "HealthMaximum", 1200.0f);
    }
}