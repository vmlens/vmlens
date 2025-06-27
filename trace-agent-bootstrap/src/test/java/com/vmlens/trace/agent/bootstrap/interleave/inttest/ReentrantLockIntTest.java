package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

import com.vmlens.trace.agent.bootstrap.interleave.lock.ReentrantLockKey;

public class ReentrantLockIntTest {

    @Test
    public void enterExit() {
        // Given
        LockKey A = new ReentrantLockKey(1000L);

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
    public void chainedLocks() {
        // Given
        LockKey A = new ReentrantLockKey(1000L);
        LockKey B = new ReentrantLockKey(100L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA0 = builder.enter(A,0);
        builder.enter(B,0);
        builder.exit(A,0);
        IntTestOperation exitB0 = builder.exit(B,0);

        IntTestOperation enterA1 = builder.enter(A,1);
        builder.enter(B,1);
        builder.exit(A,1);
        IntTestOperation exitB1 = builder.exit(B,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exitB0,enterA1);
        expectedBuilder.group(exitB1,enterA0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }
}
