package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class EnterExitContext implements EventContext{
    private final Object object;
    private final int methodId;
    private final InTestActionProcessor inTestActionProcessor;
    private final ReadWriteLockMap readWriteLockMap;
    private final Object returnValue;
    private final Integer intParam;

    public EnterExitContext(Object object,
                            int methodId,
                            InTestActionProcessor inTestActionProcessor,
                            ReadWriteLockMap readWriteLockMap,
                            Object returnValue,
                            Integer intParam) {
        this.object = object;
        this.methodId = methodId;
        this.inTestActionProcessor = inTestActionProcessor;
        this.readWriteLockMap = readWriteLockMap;
        this.returnValue = returnValue;
        this.intParam = intParam;
    }

    public Object object() {
        return object;
    }

    public int methodId() {
        return methodId;
    }

    public Object returnValue() {
        return returnValue;
    }

    public ReadWriteLockMap readWriteLockMap() {
        return readWriteLockMap;
    }

    public int intParam() {
        return intParam;
    }

    public InTestActionProcessor inTestActionProcessor() {
        return inTestActionProcessor;
    }
}
