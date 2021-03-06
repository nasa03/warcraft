/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.common.preferences;

import com.badlogic.gdx.Preferences;
import com.evilbird.engine.common.collection.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Preferences Preference} implementation that only stores property
 * values in memory.
 *
 * @author Blair Butterworth
 */
public class TransientPreferences implements Preferences 
{
    private Map<String, Object> properties;

    public TransientPreferences() {
        properties = new HashMap<>();
    }
    
    @Override
    public Preferences putBoolean(String key, boolean value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences putInteger(String key, int value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences putLong(String key, long value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences putFloat(String key, float value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences putString(String key, String value) {
        properties.put(key, value);
        return this;
    }

    @Override
    public Preferences put(Map<String, ?> values) {
        properties.putAll(values);
        return this;
    }

    @Override
    public boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    @Override
    public boolean getBoolean(String key, boolean defValue) {
        return (boolean)Maps.getOrDefault(properties, key, defValue);
    }

    @Override
    public int getInteger(String key) {
        return getInteger(key, 0);
    }

    @Override
    public int getInteger(String key, int defValue) {
        return (int)Maps.getOrDefault(properties, key, defValue);
    }

    @Override
    public long getLong(String key) {
        return getLong(key, 0);
    }

    @Override
    public long getLong(String key, long defValue) {
        return (long)Maps.getOrDefault(properties, key, defValue);
    }

    @Override
    public float getFloat(String key) {
        return getFloat(key, 0);
    }

    @Override
    public float getFloat(String key, float defValue) {
        return (float)Maps.getOrDefault(properties, key, defValue);
    }

    @Override
    public String getString(String key) {
        return getString(key, "");
    }

    @Override
    public String getString(String key, String defValue) {
        return (String)Maps.getOrDefault(properties, key, defValue);
    }

    @Override
    public Map<String, ?> get() {
        return properties;
    }

    @Override
    public boolean contains(String key) {
        return properties.containsKey(key);
    }

    @Override
    public void clear() {
        properties.clear();
    }

    @Override
    public void flush() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(String key) {
        properties.remove(key);
    }
}
