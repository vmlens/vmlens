package com.vmlens.trace.agent.bootstrap.callback.callbackaction.initializationaction;

import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class StrategyInitializeContext {

    private final Object returnValue;
    private final Object object;
    private final ReadWriteLockMap readWriteLockMap;

    public StrategyInitializeContext(Object returnValue,
                                     Object object,
                                     ReadWriteLockMap readWriteLockMap) {
        this.returnValue = returnValue;
        this.object = object;
        this.readWriteLockMap = readWriteLockMap;
    }

    public Object returnValue() {
        return returnValue;
    }

    public Object object() {
        return object;
    }

    public ReadWriteLockMap readWriteLockMap() {
        return readWriteLockMap;
    }
}
