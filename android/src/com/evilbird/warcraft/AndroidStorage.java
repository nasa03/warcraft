/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.LocalFileHandleResolver;
import com.evilbird.engine.device.DeviceStorage;

/**
 * Provides access to device persistence.
 *
 * @author Blair Butterworth
 */
public class AndroidStorage implements DeviceStorage
{
    private FileHandleResolver resolver;

    public AndroidStorage() {
        this(new LocalFileHandleResolver());
    }

    public AndroidStorage(FileHandleResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public FileHandleResolver getFileHandleResolver() {
        return resolver;
    }
}
