package com.vmlens.trace.agent.bootstrap.strategy.strategyall;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;

public class NormalMethodStrategy implements StrategyAll {

    public static final StrategyAll SINGLETON = new NormalMethodStrategy();

    private NormalMethodStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext enterExitContext) {
        enterExitContext.threadLocalWhenInTestAdapter().process(new RunAfter<>(new MethodEnterEvent(
                enterExitContext.methodId()),
                new SetFieldsNoOp<>()));
    }

}
