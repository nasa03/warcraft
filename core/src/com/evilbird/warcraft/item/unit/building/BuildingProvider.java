package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.human.BarracksProvider;
import com.evilbird.warcraft.item.unit.building.human.FarmProvider;
import com.evilbird.warcraft.item.unit.building.human.TownHallProvider;

import javax.inject.Inject;

/**
 * Instances of this class TODO:Finish
 *
 * @author Blair Butterworth
 */
public class BuildingProvider extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingProvider(
        BarracksProvider barracksProvider,
        FarmProvider farmProvider,
        TownHallProvider townHallProvider)
    {
        super();
        addProvider(UnitType.Barracks, barracksProvider);
        addProvider(UnitType.Farm, farmProvider);
        addProvider(UnitType.TownHall, townHallProvider);
    }
}