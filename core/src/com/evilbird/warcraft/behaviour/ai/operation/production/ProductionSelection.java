/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.warcraft.behaviour.ai.operation.production;

import com.badlogic.gdx.ai.btree.LeafTask;
import com.badlogic.gdx.ai.btree.Task;
import com.evilbird.warcraft.data.product.Product;
import com.evilbird.warcraft.data.product.Production;
import com.evilbird.warcraft.data.resource.ResourceSet;
import com.evilbird.warcraft.object.data.player.Player;

import javax.inject.Inject;

/**
 * A leaf task that selects the next unit or building to produce, based on the
 * order and manifest contained in the tasks blackboard.
 *
 * @author Blair Butterworth
 */
public class ProductionSelection extends LeafTask<ProductionData>
{
    @Inject
    public ProductionSelection() {
    }

    @Override
    public Status execute() {
        ProductionData data = getObject();
        Product product = getNextProduct(data);
        ResourceSet cost = getProductCost(product);

        if (playerHasResources(data, cost)) {
            data.setProduct(product);
            return Status.SUCCEEDED;
        }
        return Status.FAILED;
    }

    private Product getNextProduct(ProductionData data) {
        ProductionOrder order = data.getOrder();
        ProductionManifest manifest = data.getManifest();
        return order.getNextProduct(manifest);
    }

    private ResourceSet getProductCost(Product product) {
        if (product != null) {
            Production production = product.getProduction();
            return production.getCost();
        }
        return null;
    }

    private boolean playerHasResources(ProductionData data, ResourceSet cost) {
        if (cost != null) {
            Player player = data.getPlayer();
            return player.hasResources(cost);
        }
        return false;
    }

    @Override
    protected Task<ProductionData> copyTo(Task<ProductionData> task) {
        return task;
    }
}
