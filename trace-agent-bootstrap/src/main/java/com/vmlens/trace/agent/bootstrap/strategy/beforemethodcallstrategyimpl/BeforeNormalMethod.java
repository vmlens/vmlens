package com.vmlens.trace.agent.bootstrap.strategy.beforemethodcallstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.strategy.BeforeMethodCallStrategy;

public class BeforeNormalMethod implements BeforeMethodCallStrategy {
    @Override
    public void targetOfMethodCall(Object object,
                                   int calledMethodId,
                                   ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
