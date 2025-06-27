package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.MonitorKey;
import org.junit.Test;

public class MonitorIntTest {

    @Test
    public void enterExit() {
        // Given
        LockKey A = new MonitorKey(1000L);

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
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

    @Test
    public void deadlock() {
        // Given
        LockKey A = new MonitorKey(1000L);
        LockKey B = new MonitorKey(1500L);

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
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }
}
