package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadJoinedEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD;

public class ThreadJoinStrategy extends StrategyWithoutParam  {

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

        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }

}
