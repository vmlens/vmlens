package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;

import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.methodEnterEvent;
import static com.vmlens.trace.agent.bootstrap.strategy.strategyall.EventUtil.methodExitEvent;

public class NormalMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new NormalMethodStrategy();

    private NormalMethodStrategy() {
    }



    @Override
    public void methodEnter(MethodEnterExitContext enterExitContext) {
        methodEnterEvent(enterExitContext);
    }

    @Override
    public void methodExit(MethodEnterExitContext enterExitContext) {
        methodExitEvent(enterExitContext);
    }
}
