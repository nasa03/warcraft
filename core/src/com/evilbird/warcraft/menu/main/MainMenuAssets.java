/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.main;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.common.assets.AssetBundle;

/**
 * Provides the assets required to display an {@link MainMenu}, as well as any
 * sound effects used by it.
 *
 * @author Blair Butterworth
 */
public class MainMenuAssets extends AssetBundle
{
    public MainMenuAssets(AssetManager assetManager) {
        super(assetManager);
        register("data/textures/common/menu/button.png");
        register("data/textures/common/menu/menu.png");
        register("data/sounds/common/menu/click.mp3");
        register("data/music/13.mp3", Music.class);
        register("data/Strings/common/menu/main", I18NBundle.class);
    }

    public Drawable getBackground() {
        return getDrawable("menu.png");
    }

    public Drawable getButtonEnabled() {
        return getDrawable("button.png", 0, 0, 224, 28);
    }

    public Drawable getButtonDisabled() {
        return getDrawable("button.png", 0, 56, 224, 28);
    }

    public Drawable getButtonSelected() {
        return getDrawable("button.png", 0, 28, 224, 28);
    }

    public Music getMusic() {
        return getMusic("13.mp3");
    }

    public MainMenuStrings getStrings() {
        return new MainMenuStrings(getStrings("main"));
    }
}