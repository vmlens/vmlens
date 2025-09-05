package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class VolatileIntTest {

    @Test
    public void readWrite() {
        // Given
        VolatileKey key = new VolatileFieldKey(3, 789L);
        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation read0 = builder.read(key, 0);
        IntTestOperation write1 = builder.write(key, 1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(read0,write1);
        expectedBuilder.group(write1,read0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

    @Test
    public void readTwoWrites() {
        // Given
        VolatileKey key = new VolatileFieldKey(3, 789L);
        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation read0 = builder.read(key, 0);
        IntTestOperation write1_1 = builder.write(key, 1);
        IntTestOperation write1_2 = builder.write(key, 1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(read0,write1_1,write1_2);
        expectedBuilder.group(write1_1,read0,write1_2);
        expectedBuilder.group(write1_1,write1_2,read0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

    @Test
    public void differentObjects() {
        // Given
        VolatileKey A = new VolatileFieldKey(3, 789L);
        VolatileKey B = new VolatileFieldKey(3, 3456L);
        IntTestBuilder builder = new IntTestBuilder();

        builder.read(A, 0);
        builder.write(B, 1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        int count = new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected()).size();
        assertThat(count,is(0));
    }

}
