/*
 * Copyright (c) 2019, Blair Butterworth
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *        https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.attack;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.DelegateAction;
import com.evilbird.engine.item.Item;
import com.evilbird.warcraft.item.unit.Unit;
import com.evilbird.warcraft.item.unit.UnitType;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

public class AttackAction extends DelegateAction
{
    private BuildingAttack buildingAttack;
    private DemolitionAttack demoAttack;
    private MeleeAttack meleeAttack;
    private RangedAttack rangedAttack;

    @Inject
    public AttackAction(
        BuildingAttack buildingAttack,
        DemolitionAttack demoAttack,
        MeleeAttack meleeAttack,
        RangedAttack rangedAttack)
    {
        this.buildingAttack = buildingAttack;
        this.demoAttack = demoAttack;
        this.meleeAttack = meleeAttack;
        this.rangedAttack = rangedAttack;
    }

    @Override
    public void setItem(Item item) {
        Validate.isInstanceOf(Unit.class, item);
        delegate = getAttackAction((Unit)item);
        super.setItem(item);
    }

    private Action getAttackAction(Unit unit) {
        UnitType type = (UnitType)unit.getType();

        if (type.isBuilding()) {
            return buildingAttack;
        }
        if (type.isDemoTeam()) {
            return demoAttack;
        }
        if (type.isMelee()) {
            return meleeAttack;
        }
        return rangedAttack;
    }
}