/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.action.common.transfer;

import com.evilbird.warcraft.data.resource.ResourceContainer;
import com.evilbird.warcraft.data.resource.ResourceQuantity;
import com.evilbird.warcraft.data.resource.ResourceType;

import javax.inject.Inject;

public class ResourceTransfer
{
    private TransferEvents events;

    @Inject
    public ResourceTransfer(TransferEvents events) {
        this.events = events;
    }

    public void transfer(ResourceContainer from, ResourceContainer to) {
        transfer(from, to, from.getResources());
    }

    public void transfer(ResourceContainer from, ResourceContainer to, Iterable<ResourceQuantity> quantities) {
        for (ResourceQuantity quantity : quantities) {
            transfer(from, to, quantity);
        }
    }

    public void transfer(ResourceContainer from, ResourceContainer to, ResourceQuantity quantity) {
        setResources(from, quantity.negate());
        setResources(to, quantity);
    }

    public void setResources(ResourceContainer container, Iterable<ResourceQuantity> quantities) {
        for (ResourceQuantity quantity : quantities) {
            setResources(container, quantity);
        }
    }

    public void setResources(ResourceContainer container, ResourceQuantity quantity) {
        ResourceType resource = quantity.getType();
        float delta = quantity.getValue();

        if (delta != 0) {
            float oldValue = container.getResource(resource);
            float newValue = oldValue + delta;

            container.setResource(resource, newValue);
            events.notifyTransfer(container, resource, newValue);
        }
    }
}
