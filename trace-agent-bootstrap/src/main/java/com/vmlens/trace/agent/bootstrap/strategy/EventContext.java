package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public interface EventContext {

    int methodId();
    ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter();
    Object object();

}
