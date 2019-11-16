/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.critter.neutral;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.DeviceDisplay;
import com.evilbird.engine.game.GameContext;
import com.evilbird.test.testcase.GameFactoryTestCase;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.state.WarcraftContext;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftFaction.Orc;
import static com.evilbird.warcraft.common.WarcraftSeason.Swamp;
import static com.evilbird.warcraft.object.unit.UnitType.Seal;

/**
 * Instances of this unit test validate logic in the {@link SealFactory} class.
 *
 * @author Blair Butterworth
 */
public class SealFactoryTest extends GameFactoryTestCase<SealFactory>
{
    @Override
    protected SealFactory newFactory(DeviceDisplay display, AssetManager assets) {
        return new SealFactory(assets);
    }

    @Override
    protected Collection<GameContext> getLoadContexts() {
        return Arrays.asList(new WarcraftContext(Human, Swamp), new WarcraftContext(Orc, Swamp));
    }

    @Override
    protected Collection<Identifier> getProductIdentifiers() {
        return Collections.singleton(UnitType.Seal);
    }

    @Override
    protected Map<String, Object> getProductProperties() {
        return Maps.of("type", Seal);
    }
}