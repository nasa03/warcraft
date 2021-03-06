/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.game;

import com.badlogic.gdx.assets.AssetManager;
import com.evilbird.engine.action.ActionFactory;
import com.evilbird.engine.assets.AssetManagerFuture;
import com.evilbird.engine.behaviour.BehaviourFactory;
import com.evilbird.engine.common.concurrent.CompleteFuture;
import com.evilbird.engine.device.Device;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.engine.object.GameObjectFactory;
import com.evilbird.engine.state.StateIdentifier;

import javax.inject.Inject;
import java.util.concurrent.Future;

/**
 * Loads and unloads assets used by the game engine.
 *
 * @author Blair Butterworth
 */
public class GameAssets
{
    private AssetManager assets;
    private ActionFactory actionFactory;
    private BehaviourFactory behaviourFactory;
    private GameObjectFactory objectFactory;
    private MenuFactory menuFactory;
    private GameContext loadedContext;
    private StateIdentifier loadedState;

    @Inject
    public GameAssets(
        Device device,
        ActionFactory actionFactory,
        BehaviourFactory behaviourFactory,
        GameObjectFactory objectFactory,
        MenuFactory menuFactory)
    {
        this.assets = device.getAssetStorage();
        this.actionFactory = actionFactory;
        this.behaviourFactory = behaviourFactory;
        this.objectFactory = objectFactory;
        this.menuFactory = menuFactory;
    }

    public StateIdentifier getLoadedState() {
        return loadedState;
    }

    public GameContext getLoadedContext() {
        return loadedContext;
    }

    public Future<?> loadMenuAssets() {
        menuFactory.load();
        return new CompleteFuture<>(null);
    }

    public Future<?> loadStateAssets(StateIdentifier state, GameContext context) {
        this.loadedState = state;
        if (context != loadedContext) {
            loadedContext = context;
            unloadContext(loadedContext);
            loadContext(context);
            return new AssetManagerFuture(assets);
        }
        return new CompleteFuture<>(null);
    }

    public void finishLoading() {
        assets.finishLoading();
    }

    private void loadContext(GameContext context) {
        actionFactory.load(context);
        menuFactory.load(context);
        objectFactory.load(context);
        behaviourFactory.load(context);
    }

    private void unloadContext(GameContext context) {
        if (context != null) {
            actionFactory.unload(context);
            menuFactory.unload(context);
            objectFactory.unload(context);
            behaviourFactory.unload(context);
        }
    }
}
