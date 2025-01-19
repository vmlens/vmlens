package com.vmlens.trace.agent.bootstrap.strategy.methodenterandexitstrategyimpl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionForRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetFieldsStrategyNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalForParallelizeProvider;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;
import com.vmlens.trace.agent.bootstrap.strategy.MethodEnterExitStrategy;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class RunMethodEnterExitStrategy implements MethodEnterExitStrategy {

    private final CheckIsThreadRun checkIsThreadRun;
    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;
    private final ParallelizeFacade parallelizeFacade;
    private final QueueIn queueIn;

    public RunMethodEnterExitStrategy(CheckIsThreadRun checkIsThreadRun,
                                      ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider,
                                      ParallelizeFacade parallelizeFacade, QueueIn queueIn) {
        this.checkIsThreadRun = checkIsThreadRun;
        this.threadLocalForParallelizeProvider = threadLocalForParallelizeProvider;
        this.parallelizeFacade = parallelizeFacade;
        this.queueIn = queueIn;
    }

    @Override
    public void onMethodEnter(Object object,
                                      int methodId,
                                      OrderMap<Long> monitorOrder,
                                      ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        if (checkIsThreadRun.isThreadRun()) {
            TLinkedList<TLinkableWrapper<SerializableEvent>> events = parallelizeFacade.beginThreadMethodEnter(threadLocalForParallelizeProvider.
                            threadLocalForParallelize(),
                    new RunnableOrThreadWrapper(Thread.currentThread()));
            queueIn.offer(events);
        }
        threadLocalWhenInTestAdapter.process(new CallbackActionForRuntimeEvent<>(new MethodEnterEvent(methodId),
                new SetFieldsStrategyNoOp<>()));
    }
}
