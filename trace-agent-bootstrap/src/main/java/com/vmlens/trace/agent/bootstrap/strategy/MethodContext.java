package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldUpdaterRepository;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class MethodContext implements EventContext {

    private final Object object;
    private final int methodId;
    private final InTestActionProcessor inTestActionProcessor;
    private final Object returnValue;
    private final Object objectParam;
    private final String stringParameter;
    private final Integer intParameter;
    private final FieldUpdaterRepository fieldUpdaterRepository;

    private MethodContext(Object object,
                          int methodId,
                          InTestActionProcessor inTestActionProcessor,
                          Object returnValue,
                          Object objectParam,
                          String stringParameter,
                          Integer intParameter,
                          FieldUpdaterRepository fieldUpdaterRepository) {
        this.object = object;
        this.methodId = methodId;
        this.inTestActionProcessor = inTestActionProcessor;
        this.returnValue = returnValue;
        this.objectParam = objectParam;
        this.stringParameter = stringParameter;
        this.intParameter = intParameter;
        this.fieldUpdaterRepository = fieldUpdaterRepository;
    }

    public static MethodContext methodEnterContext(Object object,
                                                   int methodId,
                                                   Integer intParameter,
                                                   InTestActionProcessor inTestActionProcessor) {
        return new MethodContext(object,methodId,inTestActionProcessor,null,null,null,intParameter,null);
    }

    public static MethodContext methodExitContext(Object object,
                                                  int methodId,
                                                  Object returnValue,
                                                  Object objectParam,
                                                  String stringParameter,
                                                  InTestActionProcessor inTestActionProcessor,
                                                  FieldUpdaterRepository fieldUpdaterRepository) {
        return new MethodContext(object,methodId,inTestActionProcessor,returnValue,objectParam,stringParameter,null,fieldUpdaterRepository);
    }

    public  EnterExitContext toEnterExitContext(ReadWriteLockMap readWriteLockMap) {
        return new EnterExitContext(object,
                                    methodId,
                                    inTestActionProcessor,
                                    readWriteLockMap,
                                    returnValue,
                                    intParameter,
                                    fieldUpdaterRepository,
                                    objectParam,
                                    stringParameter);
    }

    public Object objectParam() {
        return objectParam;
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
