package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunNewTestTaskStarted;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartStrategy implements StrategyPreAnalyzed {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadStartStrategy();

    private ThreadStartStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        context.threadLocalWhenInTestAdapter().process(
                new RunNewTestTaskStarted(new RunnableOrThreadWrapper(context.object())));
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<ThreadStartEvent> executeRunAfter  =
                new ExecuteRunAfter<>(new ThreadStartEvent());

        context.threadLocalWhenInTestAdapter().process(
                new SetExecuteAfterOperation(executeRunAfter));
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Nothing to do
    }
}
