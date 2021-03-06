/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.object.display.control.common;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.evilbird.engine.common.lang.TextIdentifier;
import com.evilbird.test.data.assets.TestTextures;
import com.evilbird.test.data.item.TestBuildings;
import com.evilbird.test.data.item.TestCombatants;
import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.warcraft.data.upgrade.Upgrade;
import com.evilbird.warcraft.object.display.components.actions.ActionButtonType;
import com.evilbird.warcraft.object.display.components.common.IconSet;
import com.evilbird.warcraft.object.display.views.control.ControlPaneFactory;
import com.evilbird.warcraft.object.unit.Unit;
import com.evilbird.warcraft.object.unit.UnitType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.GoldProduction2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.None;
import static com.evilbird.warcraft.data.upgrade.Upgrade.OilProduction1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.OilProduction2;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WoodProduction1;
import static com.evilbird.warcraft.data.upgrade.Upgrade.WoodProduction2;
import static com.evilbird.warcraft.object.display.components.actions.ActionButtonType.StopButton;

/**
 * Instances of this unit test validate the {@link ControlPaneFactory} class.
 *
 * @author Blair Butterworth
 */
public class IconSetTest extends GameTestCase
{
    private com.evilbird.warcraft.object.display.components.common.IconSet iconSet;

    @Before
    public void setup() {
        Texture texture = TestTextures.newTestTexture();
        iconSet = new IconSet(texture);
    }

    @Test
    public void getActionButtonCustomTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        Drawable actual = iconSet.get(ActionButtonType.AttackButton, unit);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getActionButtonPartialCustomTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        Drawable actual = iconSet.get(ActionButtonType.DepositButton, unit);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUnitTypeIconTest() {
        Drawable actual = iconSet.get(UnitType.Footman);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getShipStopIconTest() {
        Unit ship = TestCombatants.newTestCombatant(new TextIdentifier("ship"), UnitType.TrollDestroyer);
        Drawable actual = iconSet.get(StopButton, ship);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUpgradeProductIconTest() {
        Unit lumberMill = TestBuildings.newTestBuilding(new TextIdentifier("LumberMill"), UnitType.LumberMill);
        Drawable actual = iconSet.get(ActionButtonType.RangedDamage1Button, lumberMill);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getSealIconTest() {
        Drawable actual = iconSet.get(UnitType.Seal);
        Assert.assertNotNull(actual);
    }

    @Test
    public void getUnitIconTest() {
        for (UnitType unitType: UnitType.values()) {
            Drawable icon = iconSet.get(unitType);
            Assert.assertNotNull(unitType.name(), icon);
        }
    }

    @Test
    public void getActionButtonIconTest() {
        Unit unit = TestCombatants.newTestCombatant(new TextIdentifier("ElvenArcher"), UnitType.ElvenArcher);
        for (ActionButtonType actionButtonType: ActionButtonType.values()) {
            Drawable icon = iconSet.get(actionButtonType, unit);
            Assert.assertNotNull(actionButtonType.name(), icon);
        }
    }

    @Test
    public void getUpgradeIconTest() {
        Unit lumberMill = TestBuildings.newTestBuilding(new TextIdentifier("LumberMill"), UnitType.LumberMill);
        for (Upgrade upgrade: Upgrade.values()) {
            if (upgrade != None
                && upgrade != GoldProduction1 && upgrade != GoldProduction2
                && upgrade != WoodProduction1 && upgrade != WoodProduction2
                && upgrade != OilProduction1 && upgrade != OilProduction2)
            {
                Drawable icon = iconSet.get(upgrade, lumberMill);
                Assert.assertNotNull(upgrade.name(), icon);
            }
        }
    }
}