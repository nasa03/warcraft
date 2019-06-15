/*
 * Copyright (c) 2019, Blair Butterworth
 *  
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *  
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building;

import com.badlogic.gdx.math.GridPoint2;
import com.evilbird.engine.common.text.CaseUtils;
import com.evilbird.warcraft.item.unit.UnitType;

/**
 * Specifies the path to assets required to display a {@link Building}, as well
 * as to play any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class BuildingAssetManifest
{
    private String base;
    private String icons;
    private String construction;
    private String destruction;
    private String destroyed;
    private String selected;
    
    public BuildingAssetManifest(UnitType unitType, GridPoint2 dimensions) {
        String faction = getFaction(unitType);
        String name = getName(unitType);
        String size = getSize(dimensions);
        String season = getSeason(unitType);
        base = "data/textures/" + faction + "/" + season + "/" + name + ".png";
        icons = "data/textures/neutral/perennial/icons.png";
        construction = "data/textures/neutral/perennial/construction" + size + ".png";
        destruction = "data/textures/neutral/winter/destroyed_site.png";
        destroyed = "data/sounds/common/building/destroyed/";
        selected = "data/sounds/common/building/selected/";
    }

    public String getBaseTexturePath() {
        return base;
    }

    public String getIconTexturePath() {
        return icons;
    }

    public String getConstructionTexturePath() {
        return construction;
    }

    public String getDestructionTexturePath() {
        return destruction;
    }

    public String getDestroyedSoundEffectPath() {
        return destroyed;
    }

    public String getSelectedSoundEffectPath() {
        return selected;
    }

    private String getName(UnitType unitType) {
        return CaseUtils.toSnakeCase(unitType.name());
    }

    private String getFaction(UnitType unitType) {
        return CaseUtils.toSnakeCase(unitType.getFaction().name());
    }

    private String getSize(GridPoint2 dimensions) {
        if (dimensions.x == 96) {
            return "_medium";
        }
        else if (dimensions.x == 128) {
            return "_large";
        }
        return "";
    }

    private String getSeason(UnitType unitType) {
        if (unitType == UnitType.CircleOfPower) {
            return "perennial";
        }
        return "winter";
    }
}