package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfieldsstrategy.SetFieldsStrategyNoOp;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade;

import java.util.Objects;

public class RunMethodStrategy implements StrategyAll {

    private final StrategyAll strategyIfNotThreadRun;

    public RunMethodStrategy(StrategyAll strategyIfNotThreadRun) {
        this.strategyIfNotThreadRun = strategyIfNotThreadRun;
    }

    @Override
    public void onMethodEnter(Object object,
                              int methodId,
                              OrderMap<Long> monitorOrder,
                              ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                              CheckIsThreadRun checkIsThreadRun,
                              ParallelizeFacade parallelizeFacade) {
        if (checkIsThreadRun.isThreadRun()) {
            parallelizeFacade.newTask(threadLocalWhenInTestAdapter.threadLocalForParallelize(),
                    new RunnableOrThreadWrapper(Thread.currentThread()));
            threadLocalWhenInTestAdapter.process(new RunAfter<>(new MethodEnterEvent(methodId),
                    new SetFieldsStrategyNoOp<>()));
        } else {
            strategyIfNotThreadRun.onMethodEnter(object,
                    methodId,
                    monitorOrder,
                    threadLocalWhenInTestAdapter,
                    checkIsThreadRun,
                    parallelizeFacade);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RunMethodStrategy that = (RunMethodStrategy) o;
        return Objects.equals(strategyIfNotThreadRun, that.strategyIfNotThreadRun);
    }

    @Override
    public int hashCode() {
        return strategyIfNotThreadRun != null ? strategyIfNotThreadRun.hashCode() : 0;
    }
}
