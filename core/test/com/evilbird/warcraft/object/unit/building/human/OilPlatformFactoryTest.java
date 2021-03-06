/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryTestCase;

import java.util.Map;

import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitAnimation.Idle;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;

/**
 * Instances of this unit test validate logic in the {@link BlacksmithFactory} class.
 *
 * @author Blair Butterworth
 */
public class OilPlatformFactoryTest extends BuildingFactoryTestCase<OilPlatformFactory>
{
    @Override
    protected UnitType getBuildType() {
        return OilPlatform;
    }

    @Override
    protected OilPlatformFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new OilPlatformFactory(assets);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of(
                "Animation", Idle,
                "Armour", 20,
                "Health", 650.0f,
                "HealthMaximum", 650.0f,
                "selectable", true,
                "selected", false,
                "sight", tiles(3),
                "type", OilPlatform);
    }
}