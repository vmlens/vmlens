package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdAndPosition;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

import static com.vmlens.trace.agent.bootstrap.event.EventTypeThread.THREAD;

public class ThreadStartStrategy extends StrategyWithoutParam {

    public static final StrategyPreAnalyzed SINGLETON = new ThreadStartStrategy();

    private ThreadStartStrategy() {
    }

    @Override
    public void methodEnter(EnterExitContext context) {

        ThreadStartEvent threadStartEvent = new ThreadStartEvent(new ThreadWrapper(context.object()),THREAD.code());

        RunBeforeLockExitOrWait<ThreadStartEvent> action = new
                RunBeforeLockExitOrWait<>(threadStartEvent,
                new SetInMethodIdAndPosition<>(context.readWriteLockMap()), new WithoutFilterActions());
        context.inTestActionProcessor().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {

        context.inTestActionProcessor().process(new RunAfterLockExitWaitOrThreadStart());
    }

}
