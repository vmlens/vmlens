package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.trace.agent.bootstrap.parallelize.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.SyncActionModel;
import com.vmlens.trace.agent.bootstrap.parallelize.testFixture.TestConfig;

public class ParallelizeFunctionalTest {

    public void runTest(TestConfig config) {
        ParallelizeFacade parallelizeFacade = new ParallelizeFacade();
        while (parallelizeFacade.advance()) {
            for (SyncActionModel syncActionModel : config.givenRun) {

            }
        }


    }


}
