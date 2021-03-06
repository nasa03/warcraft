/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.evilbird.engine.common.serialization.SerializedConstructor;
import com.evilbird.engine.common.serialization.SerializedType;
import com.evilbird.engine.object.GameObjectType;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Instances of this class uniquely identify a {@link Layer}. LayerIdentifiers
 * include the file path and layer name where layer data can be obtained. For
 * efficiency reasons (to save rereading a persisted layer) the LayerIdentifier
 * may optionally contain an instance of the raw layer data represented by the
 * LayerIdentifier.
 *
 * @author Blair Butterworth
 */
@SerializedType("LayerId")
public class LayerIdentifier implements GameObjectType
{
    private String file;
    private String name;
    private transient TiledMapTileLayer layer;

    @SerializedConstructor
    private LayerIdentifier() {
    }

    public LayerIdentifier(String file, String name, TiledMapTileLayer layer){
        this.file = file;
        this.name = name;
        this.layer = layer;
    }

    public String getFile() {
        return file;
    }

    public String getName() {
        return name;
    }

    public LayerType getType() {
        return LayerType.valueOf(name);
    }

    public TiledMapTileLayer getLayer() {
        return layer;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) { return false; }

        LayerIdentifier that = (LayerIdentifier)obj;
        return new EqualsBuilder()
            .append(file, that.file)
            .append(name, that.name)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(file)
            .append(name)
            .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("name", name)
            .append("file", file)
            .toString();
    }
}
