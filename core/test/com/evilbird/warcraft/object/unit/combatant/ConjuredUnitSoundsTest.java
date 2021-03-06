/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.combatant;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.testcase.SoundCatalogTestCase;
import com.evilbird.warcraft.object.unit.UnitType;

import java.util.Arrays;
import java.util.Collection;

import static com.evilbird.warcraft.object.unit.UnitSound.Acknowledge;
import static com.evilbird.warcraft.object.unit.UnitSound.Attack;
import static com.evilbird.warcraft.object.unit.UnitSound.Selected;

/**
 * Instances of this unit test validate logic in the {@link ConjuredUnitSounds}
 * class.
 *
 * @author Blair Butterworth
 */
public class ConjuredUnitSoundsTest extends SoundCatalogTestCase<ConjuredUnitSounds, CombatantAssets>
{
    @Override
    protected CombatantAssets newAssets(AssetManager manager) {
        return new CombatantAssets(manager, UnitType.Footman);
    }

    @Override
    protected ConjuredUnitSounds newCatalog(CombatantAssets assets) {
        return new ConjuredUnitSounds(assets);
    }

    @Override
    protected Collection<Identifier> getSoundIds() {
        return Arrays.asList(Acknowledge, Selected, Attack/*, Die*/);
    }
}