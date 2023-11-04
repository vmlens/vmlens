package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.testFixture.TestData;
import com.vmlens.trace.agent.bootstrap.testFixture.VolatileFixture;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ActualRunTest {

    @Test
    public void volatileReadAndWrite() {
        // Given
        TestData testData = new VolatileFixture().volatileReadAndWrite();
        ActualRun actualRun = new ActualRun(new ActualRunObserverNoOp());
        ActualRunContextMock context = new ActualRunContextMock();
        // When
        for (InterleaveActionWithPositionFactoryAndOrRuntimeEvent action : testData.resultTestBuilder().givenRun()) {
            actualRun.after(action, context);
        }

        // Then
        assertThat(actualRun.run(), is(testData.resultTestBuilder().factoryList()));
        assertThat(context.actualEvents(), is(testData.resultTestBuilder().givenEvents()));
    }

}
