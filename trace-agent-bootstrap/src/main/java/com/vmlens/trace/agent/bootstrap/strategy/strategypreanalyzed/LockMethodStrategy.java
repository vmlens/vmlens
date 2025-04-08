package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteRunEndAtomicAction;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadJoinedEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockOperation;
import com.vmlens.trace.agent.bootstrap.lock.LockType;

public class LockMethodStrategy implements StrategyPreAnalyzed  {

    private final LockOperation lockOperation;
    private final LockType lockType;

    public LockMethodStrategy(LockOperation lockOperation, LockType lockType) {
        this.lockOperation = lockOperation;
        this.lockType = lockType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        ExecuteRunAfter<LockEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(lockOperation.create(lockType,context.object()));

        context.threadLocalWhenInTestAdapter().process(
                new SetExecuteAfterMethodCall(runtimeEventAndSetInMethodIdAndPositionImpl));
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
