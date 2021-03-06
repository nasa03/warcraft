/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.building.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.warcraft.object.unit.building.BuildingAssets;

import static com.evilbird.warcraft.object.unit.UnitAnimation.ExtractOil;

/**
 * Defines a catalog of animations as laid out in resource extractor building
 * texture atlas files. Animations are provided for construction, destruction,
 * pre-construction, post-construction and resource extraction.
 *
 * @author Blair Butterworth
 */
public class ExtractorAnimations extends BuildingAnimations
{
    public ExtractorAnimations(BuildingAssets assets) {
        super(assets);
        gatherOil(assets.getBaseTexture(), assets.getSize());
    }

    private void gatherOil(Texture base, GridPoint2 size) {
        animation(ExtractOil)
            .withTexture(base)
            .withSequence(size.y * 2, 1)
            .withSize(size)
            .withInterval(10f)
            .singleDirection()
            .looping();
    }
}
