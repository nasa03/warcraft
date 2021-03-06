/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.spatial;

import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.object.GameObject;
import com.evilbird.engine.object.spatial.GameObjectNode;
import com.evilbird.warcraft.object.common.capability.TerrainType;
import com.evilbird.warcraft.object.layer.LayerType;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * Instances of this class filter the spatial graph, removing those nodes that
 * a given item doesn't have the capability to traverse.
 *
 * @author Blair Butterworth
 */
public class SpatialPathFilter implements Predicate<GameObjectNode>
{
    private Collection<GameObject> traversableGameObjects;
    private Collection<Identifier> traversableTypes;

    /**
     * Constructs a new ItemPathFilter that filters all path content.
     */
    public SpatialPathFilter() {
        traversableGameObjects = new ArrayList<>();
        traversableTypes = new ArrayList<>();
    }

    /**
     * Specifies that the path to which the filter is applied can contain the
     * given {@link GameObject}.
     *
     * @param gameObject an {@code Item} that can be traversed. This parameter cannot
     *             be {@code null}.
     */
    public void addTraversableItem(GameObject gameObject) {
        Objects.requireNonNull(gameObject);
        traversableGameObjects.add(gameObject);
    }

    /**
     * Specifies that the path to the filter is applied can contain
     * {@link GameObject Items} that grouped by the given movement capability.
     *
     * @param capability a MovementCapability.
     */
    public void addTraversableCapability(TerrainType capability) {
        Objects.requireNonNull(capability);
        if (capability == TerrainType.Land) {
            traversableTypes.add(LayerType.Map);
            traversableTypes.add(LayerType.Shore);
            traversableTypes.add(UnitType.CircleOfPower);
        }
        else if (capability == TerrainType.ShallowWater) {
            traversableTypes.add(LayerType.Sea);
            traversableTypes.add(LayerType.Shore);
            traversableTypes.add(UnitType.OilPatch);
        }
        else if (capability == TerrainType.Water) {
            traversableTypes.add(LayerType.Sea);
            traversableTypes.add(UnitType.OilPatch);
        }
        else if (capability == TerrainType.Air) {
            traversableTypes.addAll(Arrays.asList(LayerType.values()));
            traversableTypes.addAll(Arrays.asList(UnitType.values()));
        }
    }

    @Override
    public boolean test(GameObjectNode node) {
        for (GameObject occupant : node.getOccupants()) {
            if (traversableGameObjects.contains(occupant)) {
                return true;
            }
            if (!traversableTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }

    public boolean test(GameObjectNode node, GameObject except) {
        for (GameObject occupant : node.getOccupants()) {
            if (occupant != except && traversableGameObjects.contains(occupant)) {
                return true;
            }
            if (!traversableTypes.contains(occupant.getType())) {
                return false;
            }
        }
        return true;
    }
}
