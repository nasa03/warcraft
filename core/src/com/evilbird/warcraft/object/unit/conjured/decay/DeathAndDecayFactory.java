/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.unit.conjured.decay;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.game.GameContext;
import com.evilbird.engine.game.GameFactory;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.conjured.ConjuredAssets;

import javax.inject.Inject;

import static com.evilbird.engine.common.lang.TextIdentifier.objectIdentifier;
import static com.evilbird.warcraft.object.common.query.GameObjectUtils.tiles;

/**
 * Instances of this class create {@link DeathAndDecay} objects, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
public class DeathAndDecayFactory implements GameFactory<DeathAndDecay>
{
    protected AssetManager manager;
    protected ConjuredAssets assets;
    protected DeathAndDecayBuilder builder;

    @Inject
    public DeathAndDecayFactory(Device device) {
        this(device.getAssetStorage());
    }

    public DeathAndDecayFactory(AssetManager manager) {
        this.manager = manager;
    }

    @Override
    public DeathAndDecay get(Identifier type) {
        DeathAndDecay result = (DeathAndDecay)builder.build();
        result.setAttackSpeed(3);
        result.setAttackRange(tiles(2));
        result.setBasicDamage(20);
        result.setPiercingDamage(5);
        result.setIdentifier(objectIdentifier("DeathAndDecay", result));
        result.setType(UnitType.DeathAndDecay);
        result.setSize(160, 160);
        return result;
    }

    @Override
    public void load(GameContext context) {
        assets = new ConjuredAssets(manager, UnitType.DeathAndDecay);
        builder = new DeathAndDecayBuilder(assets);
        assets.load();
    }

    @Override
    public void unload(GameContext context) {
        if (assets != null) {
            assets.unload();
        }
    }
}
