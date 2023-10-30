package com.vmlens.trace.agent.bootstrap.parallelize;

import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.testFixture.ParallelizeActionAndThreadLocalWrapper;
import com.vmlens.trace.agent.bootstrap.testFixture.TestData;
import com.vmlens.trace.agent.bootstrap.testFixture.VolatileFixture;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestModuleParallelize {

    @Test
    public void volatileReadAndWrite() {
        // Given
        TestData testData = new VolatileFixture().volatileReadAndWrite();

        // When
        ParallelizeFacade facade = testData.resultTestBuilder().parallelizeFacade();
        for (ParallelizeActionAndThreadLocalWrapper action : testData.resultTestBuilder().parallelizeActionAndThreadLocalWrapperList()) {
            facade.after(action.threadLocalWrapper, action.parallelizeAction);
        }

        // Then
        assertThat(testData.resultTestBuilder().actualRun(), is(testData.resultTestBuilder().expectedRun()));


    }


}
