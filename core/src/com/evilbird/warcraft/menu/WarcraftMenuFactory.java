/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.menu;

import com.evilbird.engine.game.GameFactorySet;
import com.evilbird.engine.menu.Menu;
import com.evilbird.engine.menu.MenuFactory;
import com.evilbird.warcraft.menu.ingame.IngameMenuFactory;
import com.evilbird.warcraft.menu.ingame.IngameMenuType;
import com.evilbird.warcraft.menu.intro.IntroMenuFactory;
import com.evilbird.warcraft.menu.intro.IntroMenuType;
import com.evilbird.warcraft.menu.main.MainMenuFactory;
import com.evilbird.warcraft.menu.main.MainMenuType;
import com.evilbird.warcraft.menu.outro.OutroMenuFactory;
import com.evilbird.warcraft.menu.outro.OutroMenuType;
import com.evilbird.warcraft.state.WarcraftContext;

import javax.inject.Inject;

import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.common.WarcraftSeason.Winter;

/**
 * Instances of this factory create {@link Menu Menus} whose contents are
 * specified by the given menu identifiers. This class supports creation of
 * main menus, level introduction and completion menus and in-game menus.
 *
 * @see MainMenuType
 * @see IntroMenuType
 * @see OutroMenuType
 * @see IngameMenuType
 *
 * @author Blair Butterworth
 */
public class WarcraftMenuFactory extends GameFactorySet<Menu> implements MenuFactory
{
    @Inject
    public WarcraftMenuFactory(
        MainMenuFactory mainMenuFactory,
        IntroMenuFactory introMenuFactory,
        OutroMenuFactory outroMenuFactory,
        IngameMenuFactory ingameMenuFactory)
    {
        addProvider(MainMenuType.values(), mainMenuFactory);
        addProvider(IntroMenuType.values(), introMenuFactory);
        addProvider(OutroMenuType.values(), outroMenuFactory);
        addProvider(IngameMenuType.values(), ingameMenuFactory);
    }

    @Override
    public Menu get() {
        return get(MainMenuType.Home);
    }

    @Override
    public void load() {
        load(new WarcraftContext(Human, Winter));
    }
}
