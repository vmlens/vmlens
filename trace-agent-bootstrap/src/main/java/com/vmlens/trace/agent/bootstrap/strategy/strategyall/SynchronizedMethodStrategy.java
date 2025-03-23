package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteSynchronizedMethodExit;

import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.*;

public class SynchronizedMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new SynchronizedMethodStrategy();

    private SynchronizedMethodStrategy() {
    }

    @Override
    public void methodEnter(MethodEnterExitContext enterExitContext) {


        ExecuteSynchronizedMethodExit executeSynchronizedMethodExit =
                new ExecuteSynchronizedMethodExit(createMonitorEnter(enterExitContext,-1),
                        createMethodEnter(enterExitContext));
        enterExitContext.threadLocalWhenInTestAdapter().process(new SetExecuteAfterMethodCall(executeSynchronizedMethodExit));
    }

    @Override
    public void methodExit(MethodEnterExitContext enterExitContext) {
        methodEnterEvent(enterExitContext);
        monitorEnter(enterExitContext,-1);
    }
}
