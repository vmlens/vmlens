package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionForRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetFieldsStrategyNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;

public class RunMethodEnterExitStrategy implements MethodEnterExitStrategy {

    private final CheckIsThreadRun checkIsThreadRun;
    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final ParallelizeFacade parallelizeFacade;

    public RunMethodEnterExitStrategy(CheckIsThreadRun checkIsThreadRun,
                                      ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
                                      ParallelizeFacade parallelizeFacade) {
        this.checkIsThreadRun = checkIsThreadRun;
        this.threadLocalForParallelizeProvider = threadLocalForParallelizeProvider;
        this.parallelizeFacade = parallelizeFacade;
    }

    @Override
    public void onMethodEnter(Object object,
                                      int methodId,
                                      OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        threadLocalWhenInTestAdapter.process(new CallbackActionForRuntimeEvent<>(new MethodEnterEvent(methodId),
                new SetFieldsStrategyNoOp<>()));
        if (checkIsThreadRun.isThreadRun()) {
            parallelizeFacade.beginThreadMethodEnter(threadLocalForParallelizeProvider.
                            threadLocalForParallelize(),
                    new RunnableOrThreadWrapper(Thread.currentThread()));
        }
    }

    @Override
    public void onMethodExit(Object object, int methodId,
                             OrderMap<Long> monitorOrder,
                             ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        threadLocalWhenInTestAdapter.process(new CallbackActionForRuntimeEvent<>(new MethodExitEvent(methodId),
                new SetFieldsStrategyNoOp<>()));
    }
}
