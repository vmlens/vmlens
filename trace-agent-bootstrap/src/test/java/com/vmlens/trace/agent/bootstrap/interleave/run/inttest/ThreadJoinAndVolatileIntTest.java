package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileFieldKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestOperation;
import com.vmlens.trace.agent.bootstrap.interleave.run.inttestutil.IntTestRunner;
import org.junit.Test;

public class ThreadJoinAndVolatileIntTest {

    @Test
    public void joinReadWrite() {
        // Given
        VolatileKey key = new VolatileFieldKey(3, 789L);
        IntTestBuilder builder = new IntTestBuilder();

        IntTestOperation read0 = builder.read(key, 0);
        builder.join(1,0);

        IntTestOperation write1 = builder.write(key, 1);

        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();
        expectedBuilder.group(read0,write1);
        expectedBuilder.group(write1,read0);

        // Test
        new IntTestRunner().runTest(builder.build(),expectedBuilder.build());
    }
}
