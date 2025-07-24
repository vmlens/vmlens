package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD;

public class ThreadStartStrategy implements StrategyPreAnalyzed {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadStartStrategy();

    private ThreadStartStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {

        ThreadStartEvent threadStartEvent = new ThreadStartEvent(new ThreadWrapper(context.object()),THREAD.code());

        RunBeforeLockExitOrWait<ThreadStartEvent> action = new
                RunBeforeLockExitOrWait<>(threadStartEvent,
                new SetInMethodIdAndPosition<>(context.readWriteLockMap()), new WithoutAtomic());
        context.threadLocalWhenInTestAdapter().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {

        context.threadLocalWhenInTestAdapter().process(new RunAfterLockExitWaitOrThreadStart());
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
