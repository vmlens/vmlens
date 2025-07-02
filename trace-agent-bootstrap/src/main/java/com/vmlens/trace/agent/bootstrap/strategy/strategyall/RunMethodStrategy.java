package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import java.util.Objects;

import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.methodExitEvent;
import static com.vmlens.trace.agent.bootstrap.strategy.EventUtil.newTask;

public class RunMethodStrategy implements StrategyAll {

    private final StrategyAll strategyIfNotThreadRun;

    public RunMethodStrategy(StrategyAll strategyIfNotThreadRun) {
        this.strategyIfNotThreadRun = strategyIfNotThreadRun;
    }

    @Override
    public void methodEnter(MethodEnterExitContext enterExitContext) {
        /*
         * as we always start a new thread we use Thread.currentThread() to compare the threads
         */

        if (enterExitContext.checkIsThreadRun().isThreadRun()) {
            newTask(enterExitContext);
        } else {
            strategyIfNotThreadRun.methodEnter(enterExitContext);
        }
    }

    @Override
    public void methodExit(MethodEnterExitContext enterExitContext) {
        if (enterExitContext.checkIsThreadRun().isThreadRun()) {
            methodExitEvent(enterExitContext);
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
