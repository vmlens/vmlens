package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReadWriteLockIntTest {

    @Test
    public void readWrite() {
        // Given
        LockKey READ_A =  new ReadWriteLockKey(1000L);
        LockKey WRITE_A =  new ReadWriteLockKey(1000L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enterRead(READ_A,0);
        IntTestOperation exit0 = builder.exit(READ_A,0);

        IntTestOperation enter1 = builder.enterWrite(WRITE_A,1);
        IntTestOperation exit1 = builder.exit(WRITE_A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

    @Test
    public void readOnly() {
        // Given
        LockKey READ_A =  new ReadWriteLockKey(1000L);

        IntTestBuilder builder = new IntTestBuilder();

        builder.enterRead(READ_A,0);
        builder.exit(READ_A,0);

        builder.enterRead(READ_A,1);
        builder.exit(READ_A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        int count = new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected()).size();
        assertThat(count,is(0));
    }

    @Test
    public void deadlockReadWrite() {
        // Given
        LockKey A = new ReadWriteLockKey(1000L);
        LockKey B = new ReadWriteLockKey(1500L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA_0 = builder.enterRead(A,0);
        IntTestOperation enterB_0 = builder.enterWrite(B,0);
        builder.exit(B,0);
        builder.exit(A,0);

        IntTestOperation enterB_1 = builder.enterWrite(B,1);
        IntTestOperation enterA_1 = builder.enterRead(A,1);
        builder.exit(A,1);
        builder.exit(B,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(enterA_0,enterB_1,enterA_1,enterB_0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

    @Test
    public void noDeadlockReadOnly() {
        // Given
        LockKey A = new ReadWriteLockKey(1000L);
        LockKey B = new ReadWriteLockKey(1500L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA_0 = builder.enterRead(A,0);
        IntTestOperation enterB_0 = builder.enterRead(B,0);
        builder.exit(B,0);
        builder.exit(A,0);

        IntTestOperation enterB_1 = builder.enterRead(B,1);
        IntTestOperation enterA_1 = builder.enterRead(A,1);
        builder.exit(A,1);
        builder.exit(B,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        int count = new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected()).size();
        assertThat(count,is(0));
    }

    @Test
    public void getStateAndWriteLock() {
        // Given
        LockKey A = new ReadWriteLockKey(1000L);
        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA = builder.enterWrite(A,0);
        IntTestOperation exitA =  builder.exit(A,0);

        IntTestOperation getState =  builder.getLockState(A,1);

        // Test
        checkGetState(builder, enterA, exitA, getState);
    }

    @Test
    public void getStateAndReadLock() {
        // Given
        LockKey A = new ReadWriteLockKey(1000L);
        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enterA = builder.enterRead(A,0);
        IntTestOperation exitA =  builder.exit(A,0);

        IntTestOperation getState =  builder.getLockState(A,1);

        // Test
        checkGetState(builder, enterA, exitA, getState);
    }

    private void checkGetState( IntTestBuilder builder,
                                IntTestOperation enter,
                                IntTestOperation exit,
                                IntTestOperation getState) {
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(enter,getState,exit);
        expectedBuilder.group(getState,enter);
        expectedBuilder.group(exit,getState);

        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

}
