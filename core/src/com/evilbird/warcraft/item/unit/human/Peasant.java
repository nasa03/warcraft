package com.evilbird.warcraft.item.unit.human;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.graphics.DirectionalAnimation;
import com.evilbird.engine.item.Item;
import com.evilbird.engine.utility.AssetObjectProvider;
import com.evilbird.engine.utility.Identifier;
import com.evilbird.warcraft.item.unit.AnimatedItem;
import com.evilbird.warcraft.item.unit.AnimationBuilder;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

public class Peasant implements AssetObjectProvider<Item>
{
    private AssetManager assets;

    @Inject
    public Peasant(Device device)
    {
        this.assets = device.getAssetStorage().getAssets();
    }

    @Override
    public void load()
    {
        assets.load("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        assets.load("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        assets.load("data/textures/human/perennial/peasant.png", Texture.class);
        assets.load("data/textures/neutral/perennial/decompose.png", Texture.class);
    }

    @Override
    public Item get()
    {
        Sound selected = assets.get("data/sounds/human/unit/peasant/selected_1.mp3", Sound.class);
        Sound complete = assets.get("data/sounds/human/unit/peasant/complete.mp3", Sound.class);
        Sound acknowledge = assets.get("data/sounds/human/unit/peasant/acknowledge_1.mp3", Sound.class);
        Sound construct = assets.get("data/sounds/human/unit/peasant/construct.mp3", Sound.class);

        Map<Identifier, Sound> sounds = new HashMap<Identifier, Sound>();
        sounds.put(new Identifier("Selected"), selected);
        sounds.put(new Identifier("Complete"), complete);
        sounds.put(new Identifier("Acknowledge"), acknowledge);
        sounds.put(new Identifier("Construct"), construct);

        Texture texture = assets.get("data/textures/human/perennial/peasant.png", Texture.class);
        Texture decomposeTexture = assets.get("data/textures/neutral/perennial/decompose.png", Texture.class);
        Map<Identifier, DirectionalAnimation> animations = AnimationBuilder.getAnimationSet(texture, decomposeTexture);
        animations.put(new Identifier("GatherGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("GatherWood"), animations.get(new Identifier("Attack")));
        animations.put(new Identifier("DepositGold"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("DepositWood"), animations.get(new Identifier("Hidden")));
        animations.put(new Identifier("Build"), animations.get(new Identifier("Hidden")));

        Map<Identifier, Object> properties = new HashMap<Identifier, Object>();
        properties.put(new Identifier("Type"), new Identifier("Peasant"));
        properties.put(new Identifier("Animation"), new Identifier("Idle"));
        properties.put(new Identifier("Selected"), false);
        properties.put(new Identifier("Enabled"), true);
        properties.put(new Identifier("Health"), 100f);
        properties.put(new Identifier("Gold"), 0f);
        properties.put(new Identifier("Wood"), 0f);
        properties.put(new Identifier("Sounds"), sounds);
        properties.put(new Identifier("Id"), new Identifier());

        return new AnimatedItem(properties, animations);
    }
}
