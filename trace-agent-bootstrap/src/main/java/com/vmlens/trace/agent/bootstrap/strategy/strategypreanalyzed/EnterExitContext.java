package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.NewTaskContext;

public class EnterExitContext implements NewTaskContext {
    private final Object object;
    private final int methodId;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final ReadWriteLockMap readWriteLockMap;
    private final ParallelizeFacade parallelizeFacade;

    private Object returnValue;
    private int intParam;

    public EnterExitContext(Object object,
                            int methodId,
                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                            ReadWriteLockMap readWriteLockMap,
                            ParallelizeFacade parallelizeFacade) {
        this.object = object;
        this.methodId = methodId;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.readWriteLockMap = readWriteLockMap;
        this.parallelizeFacade = parallelizeFacade;
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

    public int intParam() {
        return intParam;
    }

    public void setIntParam(int intParam) {
        this.intParam = intParam;
    }

    @Override
    public ParallelizeFacade parallelizeFacade() {
        return parallelizeFacade;
    }
}
