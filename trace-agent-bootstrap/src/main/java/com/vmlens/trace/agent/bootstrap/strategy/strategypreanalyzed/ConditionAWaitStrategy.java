package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.AtomicBegin;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.AtomicEnd;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ConditionWaitEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ConditionWaitExitEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

/**
 *  methodEnter call waitCallOrBeforeLockExit with wait enter
 *  methodExit call waitCallOrBeforeLockExit with wait exit
 *                  afterLockExitOrWait
 *
 *  strategy for condition.await
 *
 */

public class ConditionAWaitStrategy implements StrategyPreAnalyzed  {

    @Override
    public void methodEnter(EnterExitContext context) {
        ConditionWaitEnterEvent event = new ConditionWaitEnterEvent(context.readWriteLockMap());
        RunBeforeLockExitOrWait<ConditionWaitEnterEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicBegin());
        context.inTestActionProcessor().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ConditionWaitExitEvent event = new ConditionWaitExitEvent(context.readWriteLockMap());
        RunBeforeLockExitOrWait<ConditionWaitExitEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicEnd());
        context.inTestActionProcessor().process(action);
        context.inTestActionProcessor().process(new RunAfterLockExitWaitOrThreadStart());
    }


}
