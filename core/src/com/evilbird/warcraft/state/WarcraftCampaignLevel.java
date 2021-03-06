/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.state;

import com.evilbird.warcraft.common.WarcraftFaction;

import static com.evilbird.engine.common.file.FileType.TMX;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;

/**
 * Defines states shipped in the application bundle. I.e., built in levels and
 * scenarios.
 *
 * @author Blair Butterworth
 */
public enum WarcraftCampaignLevel implements Level
{
    Human1,
    Human2,
    Human3,
    Human4,
    Human5,
    Human6,
    Human7,
    Human8,
    Human9,
    Human10,
    Human11,
    Human12,
    Human13,
    Human14,

    Orc1,
    Orc2,
    Orc3,
    Orc4,
    Orc5,
    Orc6,
    Orc7,
    Orc8,
    Orc9,
    Orc10,
    Orc11,
    Orc12,
    Orc13,
    Orc14;

    public WarcraftFaction getFaction() {
        return this.ordinal() >= Orc1.ordinal() ? Orc : Human;
    }

    public String getFactionName() {
        return getFaction().name().toLowerCase();
    }

    public String getFileName() {
        return "campaign" + getIndex() + TMX.getFileExtension();
    }

    public String getFilePath() {
        return "data/levels/" + getFactionName() + "/" + getFileName();
    }

    public int getIndex() {
        return ordinal() >= Orc1.ordinal() ? ordinal() - Orc1.ordinal() + 1 : ordinal() + 1;
    }
}
