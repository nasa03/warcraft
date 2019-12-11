/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.object.unit.combatant.gatherer;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.common.audio.sound.Sound;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.CombatantAssets;

/**
 * Provides access to the assets that are required to display a
 * {@link Gatherer}, as well as any sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class GathererAssets extends CombatantAssets
{
    public GathererAssets(AssetManager manager, UnitType type) {
        super(manager, type);
        registerTextures();
        registerSounds();
    }

    private void registerTextures() {
        registerOptional("moveWithGold", "data/textures/${faction}/unit/${name}_with_gold.png");
        registerOptional("moveWithWood", "data/textures/${faction}/unit/${name}_with_wood.png");
        registerOptional("moveWithOil", "data/textures/${faction}/unit/${name}_with_oil.png");

        registerOptional("moveWithGoldMask", "data/textures/${faction}/unit/${name}_with_gold_mask.png");
        registerOptional("moveWithWoodMask", "data/textures/${faction}/unit/${name}_with_wood_mask.png");
        registerOptional("moveWithOilMask", "data/textures/${faction}/unit/${name}_with_oil_mask.png");
    }

    private void registerSounds() {
        register("construct", "data/sounds/common/unit/construct/1.mp3");
        register("complete", "data/sounds/${faction}/unit/${name}/complete/1.mp3");

        registerOptionalSequence("attack", "data/sounds/common/unit/attack/sword/", ".mp3", 3);
        registerOptionalSequence("chopping", "data/sounds/common/unit/chopping/", ".mp3", 4);
    }

    public Texture getMoveWithGoldTexture() {
        return getOptionalTexture("moveWithGold");
    }

    public Texture getMoveWithGoldMask() {
        return getOptionalTexture("moveWithGoldMask");
    }

    public Texture getMoveWithOilTexture() {
        return getOptionalTexture("moveWithOil");
    }

    public Texture getMoveWithOilMask() {
        return getOptionalTexture("moveWithOilMask");
    }

    public Texture getMoveWithWoodTexture() {
        return getOptionalTexture("moveWithWood");
    }

    public Texture getMoveWithWoodMask() {
        return getOptionalTexture("moveWithWoodMask");
    }

    public Sound getChoppingSound() {
        return getSoundEffectSet("chopping", 4);
    }

    public Sound getSelectedSound() {
        return getSoundEffectSet("selected", 6);
    }

    public Sound getAcknowledgeSound() {
        return getSoundEffectSet("acknowledge", 4);
    }

    public Sound getAttackSound() {
        return getSoundEffectSet("attack", 3);
    }

    public Sound getCompleteSound() {
        return getSoundEffect("complete");
    }

    public Sound getConstructSound() {
        return getSoundEffect("construct");
    }
}
