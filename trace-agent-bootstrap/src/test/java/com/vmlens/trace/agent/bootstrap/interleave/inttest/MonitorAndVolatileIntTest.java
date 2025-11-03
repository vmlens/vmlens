package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

public class MonitorAndVolatileIntTest {

    @Test
    public void volatileFieldAndMonitor() {
        // Given
        LockKey A = new MonitorKey(1000L);
        VolatileKey key = new VolatileFieldKey(3, 789L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enterWrite(A,0);
        builder.read(key, 0);
        IntTestOperation exit0 = builder.exit(A,0);

        IntTestOperation enter1 = builder.enterWrite(A,1);
        builder.write(key, 1);
        IntTestOperation exit1 = builder.exit(A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

}
