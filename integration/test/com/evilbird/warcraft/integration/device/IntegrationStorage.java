/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.integration.device;

import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.ExternalFileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evilbird.engine.common.file.RelativeFileHandleResolver;
import com.evilbird.engine.device.DeviceStorage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides access to device persistence. For desktop platforms this is located
 * in the ".warcraft" directory located in the users home directory.
 *
 * @author Blair Butterworth
 */
public class IntegrationStorage implements DeviceStorage
{
    private static final String STORAGE_ROOT = ".warcraft2" + File.separator;

    private FileHandleResolver resolver;

    public IntegrationStorage() {
        this(new ExternalFileHandleResolver());
    }

    public IntegrationStorage(FileHandleResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    public FileHandleResolver getFileHandleResolver() {
        return new RelativeFileHandleResolver(STORAGE_ROOT, resolver);
    }

    @Override
    public List<String> list(String path) throws IOException {
        try {
            FileHandle handle = resolver.resolve(STORAGE_ROOT + path);
            return convert(handle.list());
        }
        catch (GdxRuntimeException error) {
            throw new IOException(error);
        }
    }

    private List<String> convert(FileHandle[] handles) {
        List<String> result = new ArrayList<>();
        for (FileHandle handle : handles) {
            result.add(handle.path());
        }
        return result;
    }

    @Override
    public Reader read(String path) throws IOException {
        try {
            FileHandle handle = resolver.resolve(STORAGE_ROOT + path);
            return handle.reader();
        }
        catch (GdxRuntimeException error) {
            throw new IOException(error);
        }
    }

    @Override
    public Writer write(String path) throws IOException {
        try {
            FileHandle handle = resolver.resolve(STORAGE_ROOT + path);
            createDirectory(handle);
            return handle.writer(false);
        }
        catch (GdxRuntimeException error) {
            throw new IOException(error);
        }
    }

    private void createDirectory(FileHandle handle) {
        if (! handle.isDirectory()) {
            createDirectory(handle.parent());
        } else {
            handle.mkdirs();
        }
    }

    @Override
    public void remove(String path) throws IOException {
        try {
            FileHandle handle = resolver.resolve(STORAGE_ROOT + path);
            handle.delete();
        }
        catch (GdxRuntimeException error) {
            throw new IOException(error);
        }
    }
}