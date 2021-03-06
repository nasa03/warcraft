/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.evilbird.engine.common.file.FileType;

/**
 * Contains common functions for working with LibGDX assets.
 *
 * @author Blair Butterworth
 */
public class AssetUtilities
{
    private AssetUtilities() {
    }

    public static void loadSet(AssetManager assets, String prefix, String suffix, int count, Class<?> type) {
        for (int i = 1; i <= count; i++){
            assets.load(prefix + i + suffix, type);
        }
    }

    public static void loadSoundSet(AssetManager assets, String prefix, FileType type, int count) {
        loadSet(assets, prefix, type.getFileExtension(), count, Sound.class);
    }

    public static FontLoaderParameters fontSize(int size) {
        FreeTypeFontParameter style = new FreeTypeFontParameter();
        style.size = size;
        style.minFilter = Texture.TextureFilter.Linear;
        style.magFilter = Texture.TextureFilter.Linear;
        return new FontLoaderParameters(style);
    }

    public static TmxMapLoader.Parameters linearFilter(){
        TmxMapLoader.Parameters parameters = new TmxMapLoader.Parameters();
        parameters.textureMinFilter = Texture.TextureFilter.Linear;
        parameters.textureMagFilter = Texture.TextureFilter.Nearest;
        return parameters;
    }
}
