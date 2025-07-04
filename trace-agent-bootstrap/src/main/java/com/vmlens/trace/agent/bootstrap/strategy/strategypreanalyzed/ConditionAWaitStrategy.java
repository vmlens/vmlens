package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfterLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.AtomicBegin;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.AtomicEnd;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ConditionWaitEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ConditionWaitExitEvent;

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
        context.threadLocalWhenInTestAdapter().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ConditionWaitExitEvent event = new ConditionWaitExitEvent(context.readWriteLockMap());
        RunBeforeLockExitOrWait<ConditionWaitExitEvent> action = new
                RunBeforeLockExitOrWait<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new AtomicEnd());
        context.threadLocalWhenInTestAdapter().process(action);
        context.threadLocalWhenInTestAdapter().process(new RunAfterLockExitOrWait());
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {

    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {

    }
}
