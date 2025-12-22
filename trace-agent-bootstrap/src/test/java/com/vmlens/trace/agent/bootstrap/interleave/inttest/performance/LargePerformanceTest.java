package com.vmlens.trace.agent.bootstrap.interleave.inttest.performance;

import com.vmlens.trace.agent.bootstrap.interleave.AbstractInterleaveActionBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContextBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.ExpectedBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.inttest.util.IntTestRunner;
import com.vmlens.trace.agent.bootstrap.mocks.QueueInMock;
import org.junit.Ignore;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.TraceFlags.TRACE_INTERLEAVE_INT_TEST_PERFORMANCE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.lessThan;

public class LargePerformanceTest {

    /*
     * currently fails on github:
     * Error:  Errors:
     * Error:    LargePerformanceTest.testH2 » StackOverflow
     */
    @Ignore
    @Test
    public void testH2() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        /**
         * currently takes for 25 alternating order 2 min
         */
        new IntTestRunner().runTest(new InterleaveActionH2().build(),expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(15).build(new QueueInNoOp(),0));
        if(TRACE_INTERLEAVE_INT_TEST_PERFORMANCE) {
            System.out.println("took " + (System.currentTimeMillis() - start));
        }
        assertThat(System.currentTimeMillis() - start, lessThan(5*1000L));
    }

    /*
    1
0
4095
2047
63487
2047
30719
2047
16383
8191


     */

    @Ignore
    @Test
    public void testH2PerformanceProblem() {
            System.out.println("1");
           // runOneH2(new InterleaveActionH2_1());
            System.out.println("2");
           // runOneH2(new InterleaveActionH2_2());
            System.out.println("3");
           // runOneH2(new InterleaveActionH2_3());
            System.out.println("4");
            runOneH2(new InterleaveActionH2_4());
    }

    private void runOneH2(AbstractInterleaveActionBuilder abstractInterleaveActionBuilder) {
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        new IntTestRunner().runTest(abstractInterleaveActionBuilder.build(),expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(50).build(new QueueInNoOp(),0));

    }


    @Test
    public void testNonBlockingAtomic() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        /**
         * currently takes for 25 alternating order 40s
         */

        new IntTestRunner().runTest(new InterleaveActionNonBlockingAtomic().build(),expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(20).build(new QueueInNoOp(),0));
        if(TRACE_INTERLEAVE_INT_TEST_PERFORMANCE) {
            System.out.println("took " + (System.currentTimeMillis() - start)); 
        }
        assertThat(System.currentTimeMillis() - start, lessThan(5*1000L));
    }

    @Test
    public void petersonLockVolatile() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        new IntTestRunner().runTest(new InterleaveActionPetersonLockVolatile().build(),
                expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().build(new QueueInMock(),0));

        // Using pattern matching this test is now fast enough
        // Typically should be lower than 200ms
        assertThat(System.currentTimeMillis() - start, lessThan(5*1000L));
    }

}
