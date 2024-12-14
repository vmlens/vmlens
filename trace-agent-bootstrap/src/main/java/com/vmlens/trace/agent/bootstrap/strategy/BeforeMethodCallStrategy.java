package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public interface BeforeMethodCallStrategy {

    void targetOfMethodCall(Object object,
                            int calledMethodId,
                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);


}
