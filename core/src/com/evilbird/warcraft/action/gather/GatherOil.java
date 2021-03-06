/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.action.Action;
import com.evilbird.engine.action.framework.SequenceAction;
import com.evilbird.engine.action.framework.TransitionAction;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.action.move.MoveToItemAction;
import com.evilbird.warcraft.common.WarcraftFaction;
import com.evilbird.warcraft.object.unit.UnitType;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.gather.GatherLocations.closestDepot;
import static com.evilbird.warcraft.action.gather.GatherLocations.closestResource;
import static com.evilbird.warcraft.common.WarcraftFaction.Human;
import static com.evilbird.warcraft.data.resource.ResourceType.Oil;
import static com.evilbird.warcraft.object.common.query.UnitOperations.hasResources;
import static com.evilbird.warcraft.object.unit.UnitType.OilPlatform;
import static com.evilbird.warcraft.object.unit.UnitType.OilRig;

/**
 * Instances of this {@link Action} instruct an {@link GameObject} to gather gold.
 *
 * @author Blair Butterworth
 */
public class GatherOil extends TransitionAction
{
    private transient Action obtain;
    private transient Action deposit;

    @Inject
    public GatherOil(
        GatherDeposit deposit,
        GatherObtainOil obtain,
        MoveToItemAction moveToDepot,
        MoveToItemAction moveToResource)
    {
        setIdentifier(GatherActions.GatherOil);
        obtain.setResource(Oil);
        deposit.setResource(Oil);
        this.deposit = add(new SequenceAction(moveToDepot, deposit));
        this.obtain = add(new SequenceAction(moveToResource, obtain));
    }

    @Override
    protected Action nextAction(Action previous) {
        return nextAction((Gatherer) getSubject(), getTarget());
    }

    private Action nextAction(Gatherer gatherer, GameObject target) {
        if (hasResources(gatherer, Oil)) {
            return getDepositAction(gatherer);
        } else {
            return getObtainAction(gatherer, target);
        }
    }

    private Action getObtainAction(Gatherer gatherer, GameObject target) {
        GameObject resource = closestResource(gatherer, target, extractorType(gatherer));
        if (resource != null) {
            obtain.setTarget(resource);
            return obtain;
        } else {
            //setFailed("Unable to locate resource of type oil");
            return null;
        }
    }

    private Action getDepositAction(Gatherer gatherer) {
        GameObject depot = closestDepot(gatherer, Oil);
        if (depot != null) {
            deposit.setTarget(depot);
            return deposit;
        } else {
            //setFailed("Unable to locate depot for oil");
            return null;
        }
    }

    private UnitType extractorType(Gatherer gatherer) {
        UnitType type = (UnitType)gatherer.getType();
        WarcraftFaction faction = type.getFaction();
        return faction == Human ? OilPlatform : OilRig;
    }
}