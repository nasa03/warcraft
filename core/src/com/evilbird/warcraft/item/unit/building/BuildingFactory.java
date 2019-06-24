/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.evilbird.engine.common.inject.IdentifiedAssetProviderSet;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.building.human.HumanBuildingFactory;
import com.evilbird.warcraft.item.unit.building.neutral.NeutralBuildingFactory;
import com.evilbird.warcraft.item.unit.building.orc.OrcBuildingFactory;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Building Buildings}, a
 * {@link Unit} specialization that provides the user the ability to train
 * combatants and research upgrades.
 *
 * @author Blair Butterworth
 */
public class BuildingFactory extends IdentifiedAssetProviderSet<Item>
{
    @Inject
    public BuildingFactory(
        HumanBuildingFactory humanBuildingFactory,
        NeutralBuildingFactory neutralBuildingFactory,
        OrcBuildingFactory orcBuildingFactory)
    {
        addProvider(humanBuildingFactory);
        addProvider(neutralBuildingFactory);
        addProvider(orcBuildingFactory);
    }
}