package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class MethodContext implements EventContext {

    private final Object object;
    private final int methodId;
    private final InTestActionProcessor inTestActionProcessor;
    private final Object returnValue;
    private final Integer intParameter;

    private MethodContext(Object object,
                          int methodId,
                          InTestActionProcessor inTestActionProcessor,
                          Object returnValue,
                          Integer intParameter) {
        this.object = object;
        this.methodId = methodId;
        this.inTestActionProcessor = inTestActionProcessor;
        this.returnValue = returnValue;
        this.intParameter = intParameter;
    }

    public static MethodContext methodEnterContext(Object object,
                                                   int methodId,
                                                   Integer intParameter,
                                                   InTestActionProcessor inTestActionProcessor) {
        return new MethodContext(object,methodId,inTestActionProcessor,null,intParameter);
    }

    public static MethodContext methodExitContext(Object object,
                                                  int methodId,
                                                  Object returnValue,
                                                  InTestActionProcessor inTestActionProcessor) {
        return new MethodContext(object,methodId,inTestActionProcessor,returnValue,null);
    }

    public  EnterExitContext toEnterExitContext(ReadWriteLockMap readWriteLockMap) {
        return new EnterExitContext(object,
                                    methodId,
                                    inTestActionProcessor,
                                    readWriteLockMap,
                                    returnValue,
                                    intParameter);
    }


    public Object object() {
        return object;
    }

    public int methodId() {
        return methodId;
    }

    public InTestActionProcessor inTestActionProcessor() {
        return inTestActionProcessor;
    }
}
