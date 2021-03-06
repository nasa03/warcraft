/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.Keep;

/**
 * Instances of this class create Human Keeps, the central building of the
 * human faction and one that creates gathering units: peasants.
 *
 * @author Blair Butterworth
 */
public class KeepFactory extends BuildingFactoryBase
{
    @Inject
    public KeepFactory(Device device) {
        this(device.getAssetStorage());
    }

    public KeepFactory(AssetManager manager) {
        super(manager, Keep);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1400);
        result.setHealthMaximum(1400);
        result.setIdentifier(objectIdentifier("Keep", result));
        result.setSight(tiles(6));
        result.setType(Keep);
        result.setResource(Food, 1);
        result.setUpgrade(GoldProduction1);
        return result;
    }
}
