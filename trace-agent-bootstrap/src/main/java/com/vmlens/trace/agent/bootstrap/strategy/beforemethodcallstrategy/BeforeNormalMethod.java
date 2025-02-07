package com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

import static com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionSetThreadStartEvent.setToNull;

public class BeforeNormalMethod implements BeforeMethodCallStrategy {
    @Override
    public void targetOfMethodCall(Object object,
                                   int calledMethodId,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        threadLocalWhenInTestAdapter.process(setToNull());
    }
}
