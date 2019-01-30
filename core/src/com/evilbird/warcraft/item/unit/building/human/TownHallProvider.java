/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.item.unit.building.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.audio.SoundEffect;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.engine.item.specialized.animated.SoundIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.common.texture.TextureUtils;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitSound;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.building.Building;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import static com.evilbird.engine.common.assets.AssetUtilities.loadSoundSet;
import static com.evilbird.engine.common.audio.SoundType.MP3;
import static com.evilbird.warcraft.item.common.sound.SoundUtils.newSoundEffect;

/**
 * Instances of this class create {@link Building Town Halls}, loading the
 * necessary assets and defining the appropriate attributes.
 *
 * @author Blair Butterworth
 */
//TODO: Create construction image of size 128x128
public class TownHallProvider implements AssetProvider<Item>
{
    private static final String BASE = "data/textures/human/winter/town_hall.png";
    private static final String ICONS = "data/textures/neutral/perennial/icons.png";
    private static final String CONSTRUCTION = "data/textures/neutral/perennial/construction.png";
    private static final String DESTRUCTION = "data/textures/neutral/winter/destroyed_site.png";
    private static final String DESTROYED = "data/sounds/neutral/building/destroyed/";
    private AssetManager assets;

    @Inject
    public TownHallProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        loadTextures();
        loadSounds();
    }

    private void loadTextures() {
        assets.load(BASE, Texture.class);
        assets.load(CONSTRUCTION, Texture.class);
        assets.load(DESTRUCTION, Texture.class);
        assets.load(ICONS, Texture.class);
    }

    private void loadSounds() {
        loadSoundSet(assets, DESTROYED, MP3, 3);
    }

    @Override
    public Item get() {
        Building result = new Building();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setAvailableSounds(getSounds());
        result.setHealth(1200.0f);
        result.setHealthMaximum(1200.0f);
        result.setIcon(getIcon());
        result.setName("Town Hall");
        result.setType(UnitType.TownHall);
        result.setSize(128, 128);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get(BASE, Texture.class);
        Texture construct = assets.get(CONSTRUCTION, Texture.class);
        Texture destruction = assets.get(DESTRUCTION, Texture.class);
        return AnimationCollections.buildingAnimations(general, construct, destruction, 128, 128);
    }

    private Drawable getIcon() {
        return TextureUtils.getDrawable(assets, ICONS, 0, 304, 46, 38);
    }

    private Map<SoundIdentifier, SoundEffect> getSounds() {
        return Maps.of(UnitSound.Die, newSoundEffect(assets, DESTROYED, MP3, 3));
    }
}
