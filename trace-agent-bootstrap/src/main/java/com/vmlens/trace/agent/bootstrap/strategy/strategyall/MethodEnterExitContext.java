package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class MethodEnterExitContext extends MonitorContext {


    private final CheckIsThreadRun checkIsThreadRun;
    private final ParallelizeFacade parallelizeFacade;

    public MethodEnterExitContext(Object object,
                                  int methodId,
                                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                                  CheckIsThreadRun checkIsThreadRun,
                                  ParallelizeFacade parallelizeFacade) {
        super(object, methodId, threadLocalWhenInTestAdapter);
        this.checkIsThreadRun = checkIsThreadRun;
        this.parallelizeFacade = parallelizeFacade;
    }

    public CheckIsThreadRun checkIsThreadRun() {
        return checkIsThreadRun;
    }

    public ParallelizeFacade parallelizeFacade() {
        return parallelizeFacade;
    }
}
