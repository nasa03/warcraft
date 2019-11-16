/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.object.unit.building.Building;
import com.evilbird.warcraft.object.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.object.unit.UnitType.MageTower;

/**
 * Instances of this class create Human Mage Towers, allowing for the creation
 * of Mages.
 *
 * @author Blair Butterworth
 */
public class MageTowerFactory extends BuildingFactoryBase
{
    @Inject
    public MageTowerFactory(Device device) {
        this(device.getAssetStorage());
    }

    public MageTowerFactory(AssetManager manager) {
        super(manager, MageTower);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(500);
        result.setHealthMaximum(500);
        result.setIdentifier(objectIdentifier("MageTower", result));
        result.setSight(tiles(3));
        result.setType(MageTower);
        return result;
    }
}