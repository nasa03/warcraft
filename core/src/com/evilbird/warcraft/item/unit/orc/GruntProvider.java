package com.evilbird.warcraft.item.unit.orc;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.evilbird.engine.common.graphics.DirectionalAnimation;
import com.evilbird.engine.common.inject.AssetObjectProvider;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.common.AnimatedItem;
import com.evilbird.warcraft.item.unit.common.AnimationBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class GruntProvider implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public GruntProvider(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/textures/orc/perennial/grunt.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Texture texture = assets.get("data/textures/orc/perennial/grunt.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getAnimationSet(texture, decomposeTexture);

        Texture iconTexture = assets.get("data/textures/neutral/perennial/icons.png", Texture.class);
        TextureRegion iconRegion = new TextureRegion(iconTexture, 138, 0, 46, 38);
        TextureRegionDrawable icon = new TextureRegionDrawable(iconRegion);

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Grunt"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Id"), new Identifier());
        properties.put(new Identifier("Icon"), icon);
        properties.put(new Identifier("Health"), 60.0f);
        properties.put(new Identifier("HealthMaximum"), 60.0f);

        return new AnimatedItem(properties, animations);
    }
}
