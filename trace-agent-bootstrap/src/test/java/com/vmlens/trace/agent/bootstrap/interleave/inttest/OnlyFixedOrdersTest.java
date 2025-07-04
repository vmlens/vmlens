package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

public class OnlyFixedOrdersTest {

    @Test
    public void joinReadWrite() {
        // Given
        IntTestBuilder builder = new IntTestBuilder();
        builder.join(1,0);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

}
