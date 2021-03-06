/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ui.interaction;

import javax.inject.Inject;
import javax.inject.Provider;

import static com.evilbird.engine.device.UserInputType.Menu;
import static com.evilbird.warcraft.action.menu.MenuActions.ActionsMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildAdvancedMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.BuildSimpleMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.IngameMenu;
import static com.evilbird.warcraft.action.menu.MenuActions.MapNavigate;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionApplicability.Target;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Addition;
import static com.evilbird.warcraft.behaviour.ui.interaction.InteractionDisplacement.Standalone;
import static com.evilbird.warcraft.object.display.components.UserInterfaceComponent.MenuPane;
import static com.evilbird.warcraft.object.display.components.UserInterfaceComponent.MinimapPane;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildAdvancedButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildCancelButton;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.BuildSimpleButton;

/**
 * Defines user interactions that result in manipulation of the menu user
 * interface.
 *
 * @author Blair Butterworth
 */
public class MenuInteractions extends InteractionContainer
{
    /**
     * Constructs a new instance of this class given a {@link InteractionDefinition}
     * factory, used by {@link #addAction} to add {@link Interaction Interactions}
     * for actions.
     */
    @Inject
    public MenuInteractions(Provider<InteractionDefinition> factory) {
        super(factory);
        buildMenuInteractions();
        ingameMenuInteractions();
        minimapInteractions();
    }

    private void buildMenuInteractions() {
        addAction(ActionsMenu)
            .whenTarget(BuildCancelButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(BuildSimpleMenu)
            .whenTarget(BuildSimpleButton)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(BuildAdvancedMenu)
            .whenTarget(BuildAdvancedButton)
            .appliedTo(Target)
            .appliedAs(Addition);
    }

    private void ingameMenuInteractions() {
        addAction(IngameMenu)
            .whenTarget(MenuPane)
            .appliedTo(Target)
            .appliedAs(Addition);

        addAction(IngameMenu)
            .forInput(Menu)
            .assignedTo(item -> null)
            .appliedTo((t, s) -> null, (t, s) -> null)
            .appliedAs(Standalone);
    }

    private void minimapInteractions() {
        addAction(MapNavigate)
            .whenTarget(MinimapPane)
            .appliedTo(Target)
            .appliedAs(Addition);
    }
}
