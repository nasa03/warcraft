/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.menu.ingame;

import com.badlogic.gdx.utils.I18NBundle;

/**
 * A language specific bundle of strings used in in-game menus.
 *
 * @author Blair Butterworth
 */
public class IngameMenuStrings
{
    private I18NBundle bundle;

    public IngameMenuStrings(I18NBundle bundle) {
        this.bundle = bundle;
    }

    public String getMainTitle() {
        return bundle.get("main-title");
    }

    public String getLoadTitle() {
        return bundle.get("load-title");
    }

    public String getSaveTitle() {
        return bundle.get("save-title");
    }

    public String getOptionsTitle() {
        return bundle.get("options-title");
    }

    public String getVictoryTitle() {
        return bundle.get("victory-title");
    }

    public String getDefeatTitle() {
        return bundle.get("defeat-title");
    }

    public String getSurrenderTitle() {
        return bundle.get("surrender-title");
    }

    public String getSoundSettingsTitle() {
        return bundle.get("sound-settings-title");
    }

    public String getSpeedSettingsTitle() {
        return bundle.get("speed-settings-title");
    }

    public String getPreferenceTitle() {
        return bundle.get("preferences-title");
    }

    public String getObjectivesTitle() {
        return bundle.get("objectives-title");
    }

    public String getOkButtonText() {
        return bundle.get("ok");
    }

    public String getCancelButtonText() {
        return bundle.get("cancel");
    }

    public String getPreviousButtonText() {
        return bundle.get("previous");
    }

    public String getSaveButtonText() {
        return bundle.get("save");
    }

    public String getLoadButtonText() {
        return bundle.get("load");
    }

    public String getDeleteButtonText() {
        return bundle.get("delete");
    }

    public String getOptionsButtonText() {
        return bundle.get("options");
    }

    public String getObjectivesButtonText() {
        return bundle.get("objectives");
    }

    public String getEndButtonText() {
        return bundle.get("end");
    }

    public String getReturnButtonText() {
        return bundle.get("return");
    }

    public String getRestartButtonText() {
        return bundle.get("restart");
    }

    public String getSurrenderButtonText() {
        return bundle.get("surrender");
    }

    public String getQuitButtonText() {
        return bundle.get("quit");
    }

    public String getExitButtonText() {
        return bundle.get("exit");
    }

    public String getSoundsButtonText() {
        return bundle.get("sounds");
    }

    public String getSpeedsButtonText() {
        return bundle.get("speeds");
    }

    public String getPreferencesButtonText() {
        return bundle.get("preferences");
    }
}