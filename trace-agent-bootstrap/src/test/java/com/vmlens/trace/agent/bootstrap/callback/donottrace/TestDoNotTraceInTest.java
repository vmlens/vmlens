package com.vmlens.trace.agent.bootstrap.callback.donottrace;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.donottrace.StartDoNotTraceAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestDoNotTraceInTest {

    @Test
    public void doNotTrace() {
        // Given
        ThreadLocalWhenInTest threadLocalWhenInTest = new ThreadLocalWhenInTest(null,0);
        StacktraceDepthProvider before = new StacktraceDepthProviderMock(3);
        StacktraceDepthProvider at = new StacktraceDepthProviderMock(7);
        StacktraceDepthProvider after = new StacktraceDepthProviderMock(9);

        // When
        new StartDoNotTraceAction().checkOrSetDoNotTrace(threadLocalWhenInTest,at);

        // Then
        assertThat(threadLocalWhenInTest.processAction(after),is(false));
        assertThat(threadLocalWhenInTest.processAction(after),is(false));

        assertThat(threadLocalWhenInTest.processAction(before),is(true));
        assertThat(threadLocalWhenInTest.processAction(after),is(true));
    }

}
