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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetProvider;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.item.specialized.animated.AnimationIdentifier;
import com.evilbird.warcraft.item.common.animation.AnimationCollections;
import com.evilbird.warcraft.item.layer.LayerType;
import com.evilbird.warcraft.item.unit.UnitAnimation;
import com.evilbird.warcraft.item.unit.UnitType;
import com.evilbird.warcraft.item.unit.combatant.Combatant;

import java.util.Arrays;
import java.util.Map;

import javax.inject.Inject;

//TODO: Add sounds for selected, acknowledge, attack
public class GruntProvider implements AssetProvider<Item>
{
    private AssetManager assets;

    @Inject
    public GruntProvider(Device device) {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load() {
        assets.load("data/textures/orc/perennial/grunt.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
    }

    @Override
    public Item get() {
        Combatant result = new Combatant();
        result.setAvailableAnimations(getAnimations());
        result.setAnimation(UnitAnimation.Idle);
        result.setArmour(2f);
        result.setDamageMinimum(2f);
        result.setDamageMaximum(9f);
        result.setHealth(100f);
        result.setHealthMaximum(100f);
        result.setIcon(getIcon());
        result.setLevel(1);
        result.setName("Grunt");
        result.setMovementSpeed(64f);
        result.setMovementCapability(LayerType.Map);
        result.setRange(32 + 5);
        result.setSelected(false);
        result.setSelectable(true);
        result.setTouchable(Touchable.enabled);
        result.setSpeed(10f);
        result.setSight(256f);
        result.setType(UnitType.Grunt);
        result.setSize(32, 32);
        return result;
    }

    private Map<AnimationIdentifier, DirectionalAnimation> getAnimations() {
        Texture general = assets.get("data/textures/orc/perennial/grunt.png", Texture.class);
        Texture decompose = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        return AnimationCollections.combatantAnimations(general, decompose);
    }

    private Drawable getIcon() {
        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 138, 0, 46, 38);
        return new TextureRegionDrawable(iconRegion);
    }
}
