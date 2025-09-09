package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CallbackActionProcessorImplTest {

    @Test
    public void whenRecursiveCall_ThenTraceOnlyPossibleOnlyAtEnd() {
        // Given
        CallbackActionProcessorImpl callbackActionProcessor = new CallbackActionProcessorImpl();
        ThreadLocalForCallbackAction threadLocalForCallbackAction = new ThreadLocalForCallbackAction(
                new ThreadForParallelize(Thread.currentThread()));

        // When and Then
        callbackActionProcessor.startDoNotTrace(threadLocalForCallbackAction);
        assertThat(callbackActionProcessor.canProcess(threadLocalForCallbackAction),is(false));
        recursiveFunction(5,callbackActionProcessor,threadLocalForCallbackAction);
        assertThat(callbackActionProcessor.canProcess(threadLocalForCallbackAction),is(false));
        callbackActionProcessor.endDoNotTrace(threadLocalForCallbackAction);
        assertThat(callbackActionProcessor.canProcess(threadLocalForCallbackAction),is(true));
    }

    private void recursiveFunction(int depth,
                                   CallbackActionProcessorImpl callbackActionProcessor,
                                   ThreadLocalForCallbackAction threadLocalForCallbackAction) {
        assertThat(callbackActionProcessor.canProcess(threadLocalForCallbackAction),is(false));
        if(depth > 0) {
            recursiveFunction(depth-1 , callbackActionProcessor,threadLocalForCallbackAction);
        }
    }

}
