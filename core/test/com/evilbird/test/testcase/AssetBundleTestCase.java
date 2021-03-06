/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.testcase;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.I18NBundle;
import com.evilbird.engine.assets.AssetBundle;
import com.evilbird.engine.assets.SyntheticTexture;
import com.evilbird.test.utils.AssetFileHandleResolver;
import com.evilbird.test.utils.TestAssetManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

/**
 * Instances of this test case provide common validation for
 * {@link AssetBundle AssetBundles}.
 *
 * @author Blair Butterworth
 */
public abstract class AssetBundleTestCase<T extends AssetBundle> extends GameTestCase
{
    protected T bundle;
    protected AssetManager assets;
    protected FileHandleResolver resolver;
    protected Locale currentLocale;
    protected Locale originalLocale;

    @Before
    public void setup() {
        originalLocale = Locale.getDefault();
        currentLocale = Locale.UK;
        Locale.setDefault(currentLocale);
        resolver = new AssetFileHandleResolver();
        assets = TestAssetManager.getTestAssetManager(resolver);
        bundle = getAssetBundle(assets);
    }

    @After
    public void teardown() {
        Locale.setDefault(originalLocale);
    }

    protected abstract T getAssetBundle(AssetManager assets);

    @Test
    public void getTest() throws Exception {
        bundle.load();
        assets.finishLoading();
        for (Method method: bundle.getClass().getMethods()) {
            if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                Object result = method.invoke(bundle);
                Assert.assertNotNull(result);
            }
        }
    }

    @Test (expected = GdxRuntimeException.class)
    public void getWithoutLoadingTest() throws Throwable {
        try {
            for (Method method : bundle.getClass().getMethods()) {
                if (method.getName().startsWith("get") && method.getParameterCount() == 0) {
                    Object result = method.invoke(bundle);
                    Assert.assertNotNull(result);
                }
            }
        }
        catch (InvocationTargetException error) {
            throw error.getTargetException();
        }
    }

    @Test
    public void assetExistenceTest() {
        for (AssetDescriptor asset: bundle.getAssets()) {
            if (asset.type != SyntheticTexture.class) {
                if (asset.type == I18NBundle.class) {
                    assertExists(asset.fileName + ".properties");
                } else {
                    assertExists(asset.fileName);
                }
            }
        }
    }

    private void assertExists(String path) {
        FileHandle handle = resolver.resolve(path);
        Assert.assertTrue("Asset doesn't exist: " + path, handle.exists());
    }

    @Test
    public void loadTest() {
        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void loadAfterLoadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void loadAfterUnloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        bundle.load();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertTrue("Asset not loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void unloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertFalse("Asset still loaded: " + path, assets.isLoaded(path));
        }
    }

    @Test
    public void unloadAfterUnloadTest() {
        bundle.load();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        bundle.unload();
        assets.finishLoading();

        for (AssetDescriptor asset: bundle.getAssets()) {
            String path = asset.fileName;
            Assert.assertFalse("Asset still loaded: " + path, assets.isLoaded(path));
        }
    }
}
