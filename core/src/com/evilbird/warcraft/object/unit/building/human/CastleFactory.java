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
import static com.evilbird.warcraft.data.resource.ResourceType.Food;
import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.object.unit.UnitType.Castle;

/**
 * Instances of this class create Human Castles, the central building of the
 * human faction and one that creates gathering units: peasants.
 *
 * @author Blair Butterworth
 */
public class CastleFactory extends BuildingFactoryBase
{
    @Inject
    public CastleFactory(Device device) {
        this(device.getAssetStorage());
    }

    public CastleFactory(AssetManager manager) {
        super(manager, Castle);
    }

    @Override
    public Building get(Identifier type) {
        Building result = builder.build();
        result.setArmour(20);
        result.setHealth(1600);
        result.setHealthMaximum(1600);
        result.setIdentifier(objectIdentifier("Castle", result));
        result.setSight(tiles(9));
        result.setType(Castle);
        result.setResource(Food, 1);
        result.setUpgrade(GoldProduction2);
        return result;
    }
}
