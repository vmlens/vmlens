package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldUpdaterRepository;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class EnterExitContext implements EventContext{
    private final Object object;
    private final int methodId;
    private final InTestActionProcessor inTestActionProcessor;
    private final ReadWriteLockMap readWriteLockMap;
    private final Object returnValue;
    private final Integer intParam;
    private final FieldUpdaterRepository fieldUpdaterRepository;
    private final Object objectParam;
    private final String fieldNameParam;


    public EnterExitContext(Object object,
                            int methodId,
                            InTestActionProcessor inTestActionProcessor,
                            ReadWriteLockMap readWriteLockMap,
                            Object returnValue,
                            Integer intParam,
                            FieldUpdaterRepository fieldUpdaterRepository,
                            Object objectParam,
                            String fieldNameParam) {
        this.object = object;
        this.methodId = methodId;
        this.inTestActionProcessor = inTestActionProcessor;
        this.readWriteLockMap = readWriteLockMap;
        this.returnValue = returnValue;
        this.intParam = intParam;
        this.fieldUpdaterRepository = fieldUpdaterRepository;
        this.objectParam = objectParam;
        this.fieldNameParam = fieldNameParam;
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

    public FieldUpdaterRepository fieldUpdaterRepository() {
        return fieldUpdaterRepository;
    }

    public Object objectParam() {
        return objectParam;
    }

    public String fieldNameParam() {
        return fieldNameParam;
    }
}
