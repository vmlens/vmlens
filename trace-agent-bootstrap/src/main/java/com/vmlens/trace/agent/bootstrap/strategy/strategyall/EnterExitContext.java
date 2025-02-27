package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class EnterExitContext {

    private final Object object;
    private final int methodId;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final CheckIsThreadRun checkIsThreadRun;
    private final ParallelizeFacade parallelizeFacade;

    public EnterExitContext(Object object,
                            int methodId,
                            ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                            CheckIsThreadRun checkIsThreadRun,
                            ParallelizeFacade parallelizeFacade) {
        this.object = object;
        this.methodId = methodId;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.checkIsThreadRun = checkIsThreadRun;
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

    public CheckIsThreadRun checkIsThreadRun() {
        return checkIsThreadRun;
    }

    public ParallelizeFacade parallelizeFacade() {
        return parallelizeFacade;
    }
}
