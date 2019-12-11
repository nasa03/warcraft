/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.audio.sound.SoundCatalog;
import com.evilbird.engine.common.graphics.animation.AnimationCatalog;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantBuilder;
import com.evilbird.warcraft.object.unit.combatant.gatherer.animations.LandGathererAnimations;
import com.evilbird.warcraft.object.unit.combatant.gatherer.animations.SeaGathererAnimations;
import com.evilbird.warcraft.object.unit.combatant.gatherer.sounds.LandGathererSounds;
import com.evilbird.warcraft.object.unit.combatant.gatherer.sounds.SeaGathererSounds;

/**
 * Creates a new {@link Gatherer} instance whose visual and audible
 * presentation is defined by the given {@link GathererAssets}.
 *
 * @author Blair Butterworth
 */
public class GathererBuilder extends CombatantBuilder<Gatherer>
{
    private UnitType type;
    private GathererAssets assets;

    public GathererBuilder(GathererAssets assets, UnitType type) {
        super(assets, type);
        this.assets = assets;
        this.type = type;
    }

    @Override
    protected Gatherer newCombatant(Skin skin) {
        return new Gatherer(skin);
    }

    @Override
    protected AnimationCatalog newAnimations() {
        return type.isNavalUnit() ? new SeaGathererAnimations(assets) : new LandGathererAnimations(assets);
    }

    @Override
    protected SoundCatalog newSounds() {
        return type.isNavalUnit() ? new SeaGathererSounds(assets) : new LandGathererSounds(assets);
    }
}