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
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.warcraft.item.unit.building.Building;
import com.evilbird.warcraft.item.unit.building.BuildingFactoryBase;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.item.common.query.GameObjectUtils.tiles;
import static com.evilbird.warcraft.item.unit.UnitType.Blacksmith;

/**
 * Instances of this class create {@link Building Blacksmiths}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class BlacksmithFactory extends BuildingFactoryBase
{
    @Inject
    public BlacksmithFactory(Device device) {
        this(device.getAssetStorage());
    }

    public BlacksmithFactory(AssetManager manager) {
        super(manager, Blacksmith);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(775);
        result.setHealthMaximum(775);
        result.setIdentifier(objectIdentifier("Blacksmith", result));
        result.setSight(tiles(3));
        result.setType(Blacksmith);
        return result;
    }
}
