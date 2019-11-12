/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.selector;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.item.selector.building.BuildingSelectorFactory;
import com.evilbird.warcraft.item.selector.target.TargetSelectorFactory;

import javax.inject.Inject;

/**
 * A factory for visual guides used by the user to select items and locations
 * in the game world.
 *
 * @author Blair Butterworth
 */
public class SelectorFactory extends GameFactorySet<GameObject>
{
    @Inject
    public SelectorFactory(
        BuildingSelectorFactory buildingSelectorFactory,
        TargetSelectorFactory targetSelectorFactory)
    {
        addProvider(SelectorType.buildingSelectors(), buildingSelectorFactory);
        addProvider(SelectorType.targetSelectors(), targetSelectorFactory);
    }
}
