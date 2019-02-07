/*
 * Blair Butterworth (c) 2018
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.warcraft.action.common;

import com.badlogic.gdx.math.MathUtils;
import com.evilbird.engine.action.common.ActionTarget;
import com.evilbird.engine.action.framework.BasicAction;
import com.evilbird.engine.common.collection.Maps;
import com.evilbird.warcraft.item.common.capability.ResourceContainer;
import com.evilbird.warcraft.item.common.capability.ResourceIdentifier;
import com.evilbird.warcraft.item.data.DataType;

import java.util.Collections;
import java.util.Map;

import static com.evilbird.engine.item.ItemOperations.findAncestorByType;

/**
 * Instances of this action apply a given delta to the resources contained in a
 * given resource container.
 *
 * @author Blair Butterworth
 */
public class ResourceTransferAction extends BasicAction
{
    private ActionTarget target;
    private Map<ResourceIdentifier, Float> deltas;

    public ResourceTransferAction(ActionTarget target) {
        this(target, Collections.emptyMap());
    }

    public ResourceTransferAction(ActionTarget target, ResourceIdentifier type, float delta) {
        this(target, Maps.of(type, delta));
    }

    public ResourceTransferAction(ActionTarget target, Map<ResourceIdentifier, Float> deltas) {
        this.target = target;
        this.deltas = deltas;
    }

    public Map<ResourceIdentifier, Float> getResourceDeltas() {
        return deltas;
    }

    public void setResourceDeltas(Map<ResourceIdentifier, Float> deltas) {
        this.deltas = deltas;
    }

    @Override
    public boolean act(float time) {
        ResourceContainer container = getContainer();
        for (Map.Entry<ResourceIdentifier, Float> delta: deltas.entrySet()) {
            float oldValue = container.getResource(delta.getKey());
            float newValue = MathUtils.clamp(oldValue + delta.getValue(), 0f, Float.MAX_VALUE);
            container.setResource(delta.getKey(), newValue);
        }
        return true;
    }

    private ResourceContainer getContainer() {
        switch (target) {
            case Item: return (ResourceContainer)getItem();
            case Target: return (ResourceContainer)getTarget();
            case Parent: return (ResourceContainer)getItem().getParent();
            case Player: return (ResourceContainer)findAncestorByType(getItem(), DataType.Player);
            default: throw new UnsupportedOperationException();
        }
    }
}
