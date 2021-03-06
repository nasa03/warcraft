/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.audio.sound.Sound;
import com.evilbird.engine.audio.sound.SoundCatalog;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.TestAssetManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Instances of this test case validate SoundCatalog classes.
 *
 * @author Blair Butterworth
 */
public abstract class SoundCatalogTestCase<C extends SoundCatalog, A extends AssetBundle> extends GameTestCase
{
    protected A assets;
    protected C catalog;
    protected AssetManager manager;
    protected FileHandleResolver resolver;

    @Before
    public void setup() {
        resolver = new AssetFileHandleResolver();
        manager = TestAssetManager.getTestAssetManager(resolver);
        assets = newAssets(manager);
        assets.load();
        manager.finishLoading();
        catalog = newCatalog(assets);
    }

    protected abstract A newAssets(AssetManager manager);

    protected abstract C newCatalog(A assets);

    @Test
    public void getTest() {
        Map<Identifier, Sound> actual = catalog.get();
        Collection<Identifier> expected = getSoundIds();

        Assert.assertNotNull(actual);
        Assert.assertEquals(expected.size(), actual.size());

        for (Entry<Identifier, Sound> entry: actual.entrySet()) {
            Identifier id = entry.getKey();
            Sound sound = entry.getValue();

            Assert.assertTrue(id + " missing", expected.contains(id));
            Assert.assertNotNull(sound);
        }
    }

    protected abstract Collection<Identifier> getSoundIds();
}
