package com.vmlens.trace.agent.bootstrap.interleave.inttest.performance;

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

   @Ignore
    @Test
    public void testH2() {
        // Expected
        ExpectedBuilder expectedBuilder = new ExpectedBuilder();

        // Test
        long start = System.currentTimeMillis();

        /*
            java.lang.StackOverflowError
	at java.base/java.util.HashMap.put(HashMap.java:619)
	at java.base/java.util.HashSet.add(HashSet.java:230)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:137)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:153)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:153)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:153)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:153)
	at org.jgrapht.alg.cycle.SzwarcfiterLauerSimpleCycles.cycle(SzwarcfiterLauerSimpleCycles.java:153)
         */
        new IntTestRunner().runTest(new InterleaveActionH2().build(),expectedBuilder.buildExpected(),
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(2).build(new QueueInNoOp(),0));
        if(TRACE_INTERLEAVE_INT_TEST_PERFORMANCE) {
            System.out.println("took " + (System.currentTimeMillis() - start));
        }
    }


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
                new InterleaveLoopContextBuilder().withMaximumAlternatingOrders(20).build(new QueueInNoOp(),0));
        if(TRACE_INTERLEAVE_INT_TEST_PERFORMANCE) {
            System.out.println("took " + (System.currentTimeMillis() - start)); 
        }
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
        assertThat(System.currentTimeMillis() - start, lessThan(10 * 1000L));
    }


}
