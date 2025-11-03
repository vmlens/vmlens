package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionEnd;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.ExecuteFilterActionBegin;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockEnterOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockType;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class LockEnterStrategy extends StrategyWithoutParam  {

    private final LockEnterOperation lockOperation;
    private final LockType lockType;

    public LockEnterStrategy(LockEnterOperation lockOperation, LockType lockType) {
        this.lockOperation = lockOperation;
        this.lockType = lockType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        context.inTestActionProcessor().process(new ExecuteFilterActionBegin());
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<LockEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(lockOperation.create(lockType,context.object()));
        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl
                , new FilterActionEnd()));
    }

}
