package com.vmlens.trace.agent.bootstrap.interleave.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

public class BarrierIntTest {

    /**
     * currently barriers allow many interleavings even if
     * they are not possible in practice
     */
    @Test
    public void waitNotify() {
        // Given
        VolatileKey key = new VolatileFieldKey(3, 789L);
        FutureKey futureKey =new FutureKey(100L);

        IntTestBuilder builder = new IntTestBuilder();

        builder.read(key, 0);
        IntTestOperation wait0 = builder.barrierWait(futureKey,0);
        builder.barrierNotify(futureKey,0);


        IntTestOperation notify0 = builder.barrierNotify(futureKey,1);
        builder.barrierWait(futureKey,1);
        builder.write(key, 1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(wait0,notify0);
        expectedBuilder.group(notify0,wait0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.buildExpected());
    }

}
