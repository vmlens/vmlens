package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteSynchronizedMethodExit;

import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.*;

public class SynchronizedMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new SynchronizedMethodStrategy();

    private SynchronizedMethodStrategy() {
    }

    @Override
    public void methodEnter(MethodEnterExitContext enterExitContext) {
        methodEnterEvent(enterExitContext);
        monitorEnter(enterExitContext,-1);
    }

    @Override
    public void methodExit(MethodEnterExitContext enterExitContext) {
        ExecuteSynchronizedMethodExit executeSynchronizedMethodExit =
                new ExecuteSynchronizedMethodExit(createMonitorExit(enterExitContext,-1),
                        createMethodExit(enterExitContext));
        enterExitContext.threadLocalWhenInTestAdapter().process(new SetExecuteAfterOperation(executeSynchronizedMethodExit));
    }
}
