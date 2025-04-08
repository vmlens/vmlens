package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class EnterExitContext {
    private final Object object;
    private final int methodId;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final ReadWriteLockMap readWriteLockMap;

    private Object returnValue;

    public EnterExitContext(Object object,
                            int methodId,
                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                            ReadWriteLockMap readWriteLockMap) {
        this.object = object;
        this.methodId = methodId;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.readWriteLockMap = readWriteLockMap;
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

    public Object returnValue() {
        return returnValue;
    }

    public void setReturnValue(Object returnValue) {
        this.returnValue = returnValue;
    }

    public ReadWriteLockMap readWriteLockMap() {
        return readWriteLockMap;
    }
}
