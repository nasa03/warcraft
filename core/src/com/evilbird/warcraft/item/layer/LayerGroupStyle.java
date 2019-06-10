/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.evilbird.engine.common.collection.BitMatrix;

import java.util.Map;

/**
 * Specifies the style of a {@link LayerGroupCell} and the surrounding cells.
 * 
 * @author Blair Butterworth
 */
public class LayerGroupStyle
{
    public Cell empty;
    public Map<BitMatrix, Cell> patterns;
}