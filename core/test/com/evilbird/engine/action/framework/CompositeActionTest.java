/*
 * Copyright (c) 2020, Blair Butterworth
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package com.evilbird.engine.action.framework;

import com.badlogic.gdx.utils.Pool;
import com.evilbird.engine.object.GameObject;
import com.evilbird.test.data.action.TestBasicAction;
import com.evilbird.test.data.action.TestCompositeAction;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.warcraft.action.attack.AttackActions;
import com.evilbird.warcraft.action.move.MoveActions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Instances of this unit test validate the {@link CompositeAction} class.
 *
 * @author Blair Butterworth
 */
public class CompositeActionTest
{
    private BasicAction childA;
    private BasicAction childB;
    private BasicAction childC;
    private CompositeAction composite;

    @Before
    public void setup() {
        childA = new TestBasicAction();
        childA.setIdentifier(MoveActions.MoveToItem);

        childB = new TestBasicAction();
        childB.setIdentifier(MoveActions.MoveCancel);

        childC = new TestBasicAction();
        childC.setIdentifier(AttackActions.AttackCancel);

        composite = new TestCompositeAction(childA, childB, childC);
        composite.setIdentifier(AttackActions.Attack);
    }

//    @Test
//    public void serializeTest() throws IOException {
//        SerializationVerifier.forClass(TestCompositeAction.class)
//            .withDeserializedForm(composite)
//            .withSerializedResource("/action/framework/compositeaction.json")
//            .verify();
//    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TestCompositeAction.class)
            .withMockedTransientFields(GameObject.class)
            .withMockedType(Pool.class)
            .excludeTransientFields()
            .verify();
    }

    @Test
    public void itemTest() {
        Assert.assertNull(composite.getSubject());
        Assert.assertNull(childA.getSubject());
        Assert.assertNull(childB.getSubject());
        Assert.assertNull(childC.getSubject());

        GameObject actor = Mockito.mock(GameObject.class);
        composite.setSubject(actor);

        Assert.assertEquals(actor, composite.getSubject());
        Assert.assertEquals(actor, childA.getSubject());
        Assert.assertEquals(actor, childB.getSubject());
        Assert.assertEquals(actor, childC.getSubject());
    }
}