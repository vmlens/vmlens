package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunEndAtomicAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetInMethodIdAndPositionAtThreadLocal;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.InMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.CheckIsThreadRun;
import com.vmlens.trace.agent.bootstrap.strategy.strategyall.EnterExitContext;

public class MethodCallbackImpl {

    private final ParallelizeFacade parallelizeFacade;
    private final MethodRepositoryForCallback methodIdToStrategy;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final CheckIsThreadRun checkIsThreadRun;

    public MethodCallbackImpl(ParallelizeFacade parallelizeFacade,
                              MethodRepositoryForCallback methodIdToStrategy,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                              CheckIsThreadRun checkIsThreadRun) {
        this.parallelizeFacade = parallelizeFacade;
        this.methodIdToStrategy = methodIdToStrategy;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.checkIsThreadRun = checkIsThreadRun;
    }

    public void beforeMethodCall(int inMethodId, int position) {
        threadLocalWhenInTestAdapter.process(new SetInMethodIdAndPositionAtThreadLocal(
                new InMethodIdAndPosition(inMethodId, position)));
    }

    public void afterMethodCall(int inMethodId, int position) {
        threadLocalWhenInTestAdapter.process(new RunEndAtomicAction(inMethodId, position));
    }

    public void methodEnter(Object object, int methodId) {
        EnterExitContext enterExitContext = new EnterExitContext(object,
                methodId,
                threadLocalWhenInTestAdapter,
                checkIsThreadRun,
                parallelizeFacade);


        methodIdToStrategy.strategyAll(methodId).methodEnter(enterExitContext);
    }


    public void methodExit(Object object, int methodId) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MethodExitEvent(methodId),
                new SetFieldsNoOp<>()));
    }

}
