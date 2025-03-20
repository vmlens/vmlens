package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileAccessKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileFieldAccessKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.MonitorKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestRunner;
import org.junit.Test;

public class MonitorAndVolatileIntTest {

    @Test
    public void volatileFieldAndMonitor() {
        // Given
        LockKey A = new MonitorKey(1000L);
        VolatileAccessKey key = new VolatileFieldAccessKey(3, 789L);

        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation enter0 = builder.enter(A,0);
        builder.read(key, 0);
        IntTestOperation exit0 = builder.exit(A,0);

        IntTestOperation enter1 = builder.enter(A,1);
        builder.write(key, 1);
        IntTestOperation exit1 = builder.exit(A,1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(exit0,enter1);
        expectedBuilder.group(exit1,enter0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }

}
