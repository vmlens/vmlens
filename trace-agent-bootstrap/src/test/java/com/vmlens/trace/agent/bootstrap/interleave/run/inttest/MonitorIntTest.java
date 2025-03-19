package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestRunner;
import org.junit.Test;

public class MonitorIntTest {

    @Test
    public void enterExit() {
        // Given
        long A = 1000L;

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enter(A,0);
        IntTestOperation exit0 = builder.exit(A,0);

        IntTestOperation enter1 = builder.enter(A,1);
        IntTestOperation exit1 = builder.exit(A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }

    @Test
    public void deadlock() {
        // Given
        long A = 1000L;
        long B = 1500L;

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA_0 = builder.enter(A,0);
        IntTestOperation enterB_0 = builder.enter(B,0);
        builder.exit(B,0);
        builder.exit(A,0);

        IntTestOperation enterB_1 = builder.enter(B,1);
        IntTestOperation enterA_1 = builder.enter(A,1);
        builder.exit(A,1);
        builder.exit(B,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(enterA_0,enterB_1,enterA_1,enterB_0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }
}
