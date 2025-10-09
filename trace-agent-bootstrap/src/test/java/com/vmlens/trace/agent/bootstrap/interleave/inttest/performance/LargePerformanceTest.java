package com.vmlens.trace.agent.bootstrap.interleave.inttest.performance;

import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner.TRACE;

public class LargePerformanceTest {

    @Test
    public void testNonBlockingAtomic() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        /*
         * currently MaximumAlternatingOrders 14 take 60s
         * and 8 2s
         */
        new IntTestRunner().runTest(new InterleaveActionNonBlockingAtomic().build(),expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(8).build(new QueueInMock(),0));
        if(TRACE) {
            System.out.println("took " + (System.currentTimeMillis() - start)); 
        }
    }


    @Test
    public void h2() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        /*
         * currently MaximumAlternatingOrders 25 take 30s
         * and 20 1s
         */
        new IntTestRunner().runTest(new InterleaveActionH2().build(),
                expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(20).build(new QueueInMock(),0));
        if(TRACE) {
            System.out.println("took " + (System.currentTimeMillis() - start));
        }
    }


}
