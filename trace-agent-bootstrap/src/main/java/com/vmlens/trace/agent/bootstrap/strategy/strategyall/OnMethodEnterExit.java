package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public interface OnMethodEnterExit {

    int methodId();
    ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter();

}
