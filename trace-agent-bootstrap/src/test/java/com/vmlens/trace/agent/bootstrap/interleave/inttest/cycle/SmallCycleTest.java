package com.vmlens.trace.agent.bootstrap.interleave.inttest.cycle;

import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.performance.QueueInNoOp;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import org.junit.Test;

public class SmallCycleTest {

    @Test
    public void monitorAndVolatileSmall() {
        MonitorAndVolatileSmall monitorAndVolatileSmall = new MonitorAndVolatileSmall();
        new IntTestRunner().runTest(monitorAndVolatileSmall.build(),monitorAndVolatileSmall.expectedBuilder().buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(20).build(new QueueInNoOp(),0));
    }

    @Test
    public void multipleVolatileSmall() {
        MultipleVolatileSmall multipleVolatileSmall = new MultipleVolatileSmall();
        new IntTestRunner().runTest(multipleVolatileSmall.build(),multipleVolatileSmall.expectedBuilder().buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(20).build(new QueueInNoOp(),0));
    }

}
