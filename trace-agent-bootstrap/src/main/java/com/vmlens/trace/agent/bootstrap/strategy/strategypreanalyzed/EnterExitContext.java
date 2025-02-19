package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class EnterExitContext {
    private final Object object;
    private final int methodId;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;


    public EnterExitContext(Object object,
                            int methodId,
                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.object = object;
        this.methodId = methodId;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public Object object() {
        return object;
    }

    public int methodId() {
        return methodId;
    }

    public ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter() {
        return threadLocalWhenInTestAdapter;
    }
}
