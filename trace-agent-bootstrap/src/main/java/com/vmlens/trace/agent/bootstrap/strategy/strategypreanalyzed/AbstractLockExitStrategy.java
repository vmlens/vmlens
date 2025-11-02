package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;


import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

/*
 *  methodEnter call waitCallOrBeforeLockExit
 *  methodExit call afterLockExitOrWait
 *
 */
public abstract class AbstractLockExitStrategy extends StrategyWithoutParam  {

    @Override
    public void methodEnter(EnterExitContext context) {
        LockExitEvent event = createEvent(context);

        RunBeforeLockExitOrWait<LockExitEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new WithoutAtomic());
        context.inTestActionProcessor().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        context.inTestActionProcessor().process(new RunAfterLockExitWaitOrThreadStart());
    }

    protected abstract LockExitEvent createEvent(EnterExitContext context);


}
