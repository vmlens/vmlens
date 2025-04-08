package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicNonBlockingEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;

public class NonBlockingStrategy implements StrategyPreAnalyzed {

    private final int operation;

    public NonBlockingStrategy(int operation) {
        this.operation = operation;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        AtomicNonBlockingEvent event = new AtomicNonBlockingEvent(operation, context.object());
        event.setAtomicMethodId(context.methodId());

        ExecuteRunAfter<AtomicNonBlockingEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(event);

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
