/*
 * Blair Butterworth (c) 2019
 *
 * This work is licensed under the MIT License. To view a copy of this
 * license, visit
 *
 *      https://opensource.org/licenses/MIT
 */

package com.evilbird.engine.common.lang;

import com.evilbird.test.testcase.GameTestCase;
import com.evilbird.test.verifier.EqualityVerifier;
import com.evilbird.test.verifier.SerializationVerifier;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TextIdentifierTest extends GameTestCase
{
    private TextIdentifier identifier;

    @Before
    public void setup() {
        super.setup();
        identifier = new TextIdentifier("test");
    }

    @Test
    public void serializeTest() throws IOException {
        SerializationVerifier.forClass(Identifier.class)
                .withDeserializedForm(identifier)
                .withSerializedResource("/common/textidentifier.json")
                .verify();
    }

    @Test
    public void equalsTest() {
        EqualityVerifier.forClass(TextIdentifier.class)
                .withMockedTransientFields()
                .excludeTransientFields()
                .verify();
    }
}