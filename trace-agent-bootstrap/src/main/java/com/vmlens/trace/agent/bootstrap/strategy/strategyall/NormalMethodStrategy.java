package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfieldsstrategy.SetFieldsStrategyNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

public class NormalMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new NormalMethodStrategy();

    private NormalMethodStrategy() {
    }

    @Override
    public void onMethodEnter(Object object,
                              int methodId,
                              OrderMap<Long> monitorOrder,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                              CheckIsThreadRun checkIsThreadRun,
                              ParallelizeFacade parallelizeFacade) {
        threadLocalWhenInTestAdapter.process(new RunAfter<>(new MethodEnterEvent(methodId),
                new SetFieldsStrategyNoOp<>()));
    }

}
