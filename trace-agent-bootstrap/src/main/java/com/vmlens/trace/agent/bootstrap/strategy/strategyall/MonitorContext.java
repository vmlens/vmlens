package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class MonitorContext {

    private final Object object;
    private final int methodId;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public MonitorContext(Object object,
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
