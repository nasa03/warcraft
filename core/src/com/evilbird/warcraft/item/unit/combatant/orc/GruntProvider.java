/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.combatant.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.animated.AnimationIdentifier;
import com.evilbird.engine.item.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.engine.common.graphics.TextureUtils;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.file.FileType.MP3;
import static com.evilbird.engine.common.audio.SoundUtils.newSoundEffect;

public class GruntProvider implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/orc/perennial/grunt.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String DECOMPOSE = "data/textures/neutral/perennial/decompose.png";
    private static final String ACKNOWLEDGE = "data/sounds/orc/unit/grunt/acknowledge/";
    private static final String SELECTED = "data/sounds/orc/unit/grunt/selected/";
    private static final String ATTACK = "data/sounds/neutral/unit/attack/melee/";
    private static final String DEAD = "data/sounds/orc/unit/common/dead/";

    private AssetManager assets;

    @Inject
    public GruntProvider(Device device) {
        this.assets = device.getAssetStorage();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(ICONS, Texture.class);
        assets.load(DECOMPOSE, Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, ACKNOWLEDGE, MP3, 4);
        loadSoundSet(assets, SELECTED, MP3, 6);
        loadSoundSet(assets, ATTACK, MP3, 3);
        loadSoundSet(assets, DEAD, MP3, 1);
    }

    @Override
    public Item get() {
        Combatant result = new Combatant();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setDefence(2);
        result.setDamageMinimum(2);
        result.setDamageMaximum(9);
        result.setHealth(60);
        result.setHealthMaximum(60);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Grunt");
        result.setMovementSpeed(64); //10
        result.setMovementCapability(LayerType.Map);
        result.setRange(32 + 5); //1
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(10);
        result.setSight(4 * 32); //4
        result.setType(UnitType.Grunt);
        result.setSize(32, 32);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture decompose = assets.get(DECOMPOSE, Texture.class);
        return AnimationCollections.combatantAnimations(general, decompose);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 138, 0, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        Map<SoundIdentifier, SoundEffect> sounds = new HashMap<>();
        sounds.put(UnitSound.Acknowledge, newSoundEffect(assets, ACKNOWLEDGE, MP3, 4));
        sounds.put(UnitSound.Selected, newSoundEffect(assets, SELECTED, MP3, 6));
        sounds.put(UnitSound.Attack, newSoundEffect(assets, ATTACK, MP3, 3));
        sounds.put(UnitSound.Die, newSoundEffect(assets, DEAD, MP3, 1));
        return sounds;
    }
}
