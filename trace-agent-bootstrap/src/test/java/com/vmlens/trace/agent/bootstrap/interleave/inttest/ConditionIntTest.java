package com.vmlens.trace.agent.bootstrap.interleave.inttest;


import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

/**
 *
 * enter enterWait equals exit lock
 *
 *                 enter exit
 *
 *       exit Wait  equals enter lock  exit
 *
 */

public class ConditionIntTest {

    @Test
    public void waitNotify() {
        // Given
        LockKey A = new MonitorKey(1000L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enter(A,0);
        IntTestOperation waitEnter0 = builder.conditionWaitEnter(A,0);
        IntTestOperation waitExit0 = builder.conditionWaitExit(A,0);
        IntTestOperation exit0 = builder.exit(A,0);

        IntTestOperation enter1 = builder.enter(A,1);
        IntTestOperation exit1 = builder.exit(A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);
        expectedBuilder.group(waitEnter0,enter1);
        expectedBuilder.group(exit1,waitExit0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

}
