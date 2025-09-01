package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadJoinedEvent;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD;

public class ThreadJoinStrategy implements StrategyPreAnalyzed  {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadJoinStrategy();

    private ThreadJoinStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {

        ThreadJoinedEvent threadJoinedEvent = new ThreadJoinedEvent(((Thread) context.object()).getId());
        threadJoinedEvent.setEventType(THREAD.code());
        
        ExecuteRunAfter<ThreadJoinedEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(threadJoinedEvent);

        context.threadLocalWhenInTestAdapter().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
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
