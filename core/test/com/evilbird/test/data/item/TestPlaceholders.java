/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.test.data.item;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.evilbird.engine.common.lang.Identifier;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.engine.object.GameObjectContainer;
import com.evilbird.engine.object.GameObjectGroup;
import com.evilbird.warcraft.object.selector.SelectorType;
import com.evilbird.warcraft.object.selector.building.BuildingSelector;
import com.evilbird.warcraft.object.selector.building.BuildingSelectorStyle;

import static com.evilbird.test.data.item.TestItemRoots.newTestRoot;
import static com.evilbird.test.data.item.TestPlayers.newTestPlayer;

public class TestPlaceholders
{
    private TestPlaceholders() {
    }

    public static BuildingSelector newTestPlaceholder(String id) {
        return newTestPlaceholder(new TextIdentifier(id), SelectorType.FarmSelector);
    }

    public static BuildingSelector newTestPlaceholder(Identifier identifier, Identifier type) {
        return newTestPlaceholder(identifier, type, newTestRoot("root"), newTestPlayer("parent"));
    }

    public static BuildingSelector newTestPlaceholder(Identifier identifier, Identifier type, GameObjectContainer root, GameObjectGroup parent) {
        BuildingSelector item = new BuildingSelector(getSkin());
        item.setIdentifier(identifier);
        item.setType(type);
        item.setRoot(root);
        item.setParent(parent);
        item.setPosition(128, 128);
        item.setSize(64, 64);
        return item;
    }

    private static Skin getSkin() {
        Skin skin = new Skin();
        skin.add("default", new BuildingSelectorStyle(), BuildingSelectorStyle.class);
        return skin;
    }
}
