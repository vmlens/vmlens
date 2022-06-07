package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.test;

import com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util.ParallelizeFunctionalTest;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.VolatileFieldTestConfig;
import org.junit.Test;

public class VolatileFieldFunctionalTest {

    @Test
    public void singleField() {
        new ParallelizeFunctionalTest().runTest(VolatileFieldTestConfig.VOLATILE_FIELD_WITH_THREAD_START_AND_STOP);
    }
}
