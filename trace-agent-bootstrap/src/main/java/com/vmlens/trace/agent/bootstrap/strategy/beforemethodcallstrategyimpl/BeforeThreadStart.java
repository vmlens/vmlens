package com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;

import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionSetThreadStartEvent.setTo;
import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionSetThreadStartEvent.setToNull;

public class BeforeThreadStart implements BeforeMethodCallStrategy {
    @Override
    public void targetOfMethodCall(Object object,
                                   int calledMethodId,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        if (Thread.class == object.getClass()) {
            ThreadStartEvent threadStartEvent = new ThreadStartEvent(new RunnableOrThreadWrapper(object));
            threadLocalWhenInTestAdapter.process(setTo(threadStartEvent));
        } else {
            threadLocalWhenInTestAdapter.process(setToNull());
        }
    }
}
