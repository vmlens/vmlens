package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

import java.util.Objects;

public class RunMethodStrategy implements StrategyAll {

    private final StrategyAll strategyIfNotThreadRun;

    public RunMethodStrategy(StrategyAll strategyIfNotThreadRun) {
        this.strategyIfNotThreadRun = strategyIfNotThreadRun;
    }

    @Override
    public void methodEnter(EnterExitContext enterExitContext) {
        if (enterExitContext.checkIsThreadRun().isThreadRun()) {
            enterExitContext.parallelizeFacade().newTask(enterExitContext.
                            threadLocalWhenInTestAdapter().
                            threadLocalForParallelize(),
                    new RunnableOrThreadWrapper(Thread.currentThread()));
            enterExitContext.threadLocalWhenInTestAdapter().process(new RunAfter<>(new MethodEnterEvent(enterExitContext.methodId()),
                    new SetFieldsNoOp<>()));
        } else {
            strategyIfNotThreadRun.methodEnter(enterExitContext);
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
