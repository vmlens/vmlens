package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteSynchronizedMethodEnter;

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
        monitorExit(enterExitContext,-1);
        methodExitEvent(enterExitContext);
    }
}
