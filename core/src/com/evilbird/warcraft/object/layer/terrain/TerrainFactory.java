/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.layer.terrain;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.layer.LayerIdentifier;
import com.evilbird.warcraft.object.layer.LayerUtils;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

/**
 * Instances of this factory create {@link Terrain} layers.
 *
 * @author Blair Butterworth
 */
public class TerrainFactory implements GameFactory<Terrain>
{
    @Inject
    public TerrainFactory() {
    }

    @Override
    public void load(GameContext context) {
    }

    @Override
    public void unload(GameContext context) {
    }

    @Override
    public Terrain get(Identifier identifier) {
        Validate.isInstanceOf(LayerIdentifier.class, identifier);
        LayerIdentifier layerIdentifier = (LayerIdentifier)identifier;

        Terrain terrain = new Terrain();
        terrain.setIdentifier(layerIdentifier);
        terrain.setType(layerIdentifier.getType());
        terrain.setLayer(LayerUtils.getLayer(layerIdentifier));
        terrain.setVisible(true);
        terrain.setTouchable(Touchable.enabled);
        return terrain;
    }
}
