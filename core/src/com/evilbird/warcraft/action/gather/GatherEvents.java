/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.gather;

import com.evilbird.engine.events.Events;
import com.evilbird.engine.object.GameObject;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.object.unit.combatant.gatherer.Gatherer;

import javax.inject.Inject;

import static com.evilbird.warcraft.action.gather.GatherStatus.Cancelled;
import static com.evilbird.warcraft.action.gather.GatherStatus.DepositComplete;
import static com.evilbird.warcraft.action.gather.GatherStatus.DepositStarted;
import static com.evilbird.warcraft.action.gather.GatherStatus.ObtainComplete;
import static com.evilbird.warcraft.action.gather.GatherStatus.ObtainStarted;

/**
 * Helper class for generating gather events.
 *
 * @author Blair Butterworth
 */
public class GatherEvents
{
    private Events events;

    @Inject
    public GatherEvents(Events events) {
        this.events = events;
    }

    public void notifyObtainStarted(GameObject gatherer, GameObject resource, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, resource, quantity, ObtainStarted));
    }

    public void notifyObtainComplete(GameObject gatherer, GameObject resource, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, resource, quantity, ObtainComplete));
    }

    public void notifyDepositStarted(GameObject gatherer, GameObject depot, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, depot, quantity, DepositStarted));
    }

    public void notifyDepositComplete(GameObject gatherer, GameObject depot, ResourceQuantity quantity) {
        events.add(new GatherEvent(gatherer, depot, quantity, DepositComplete));
    }

    public void notifyGatherCancelled(Gatherer gatherer) {
        events.add(new GatherEvent(gatherer, Cancelled));
    }
}
