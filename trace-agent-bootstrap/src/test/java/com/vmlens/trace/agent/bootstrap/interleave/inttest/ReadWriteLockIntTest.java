package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.ReadWriteLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReadWriteLockIntTest {

    @Test
    public void readWrite() {
        // Given
        LockKey READ_A =  ReadWriteLockKey.readKey(1000L);
        LockKey WRITE_A =  ReadWriteLockKey.writeKey(1000L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enter(READ_A,0);
        IntTestOperation exit0 = builder.exit(READ_A,0);

        IntTestOperation enter1 = builder.enter(WRITE_A,1);
        IntTestOperation exit1 = builder.exit(WRITE_A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }

    @Test
    public void readOnly() {
        // Given
        LockKey READ_A =  ReadWriteLockKey.readKey(1000L);

        IntTestBuilder builder = new IntTestBuilder();

        builder.enter(READ_A,0);
        builder.exit(READ_A,0);

        builder.enter(READ_A,1);
        builder.exit(READ_A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        int count = new IntTestRunner().runTest(builder.build(),expectedBuilder.build()).size();
        assertThat(count,is(0));
    }

    @Test
    @Ignore
    // Fixme enable deadlock
    public void deadlockReadWrite() {
        // Given
        LockKey A = ReadWriteLockKey.readKey(1000L);
        LockKey B = ReadWriteLockKey.writeKey(1500L);

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

    @Test
    public void noDeadlockReadOnly() {
        // Given
        LockKey A = ReadWriteLockKey.readKey(1000L);
        LockKey B = ReadWriteLockKey.readKey(1500L);

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

        // Test
        int count = new IntTestRunner().runTest(builder.build(),expectedBuilder.build()).size();
        assertThat(count,is(0));
    }

}
