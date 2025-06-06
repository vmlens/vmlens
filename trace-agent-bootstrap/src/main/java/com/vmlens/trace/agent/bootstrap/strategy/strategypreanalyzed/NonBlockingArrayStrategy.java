package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicNonBlockingEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.VolatileArrayAccessEvent;

public class NonBlockingArrayStrategy implements StrategyPreAnalyzed {

    private final int operation;

    public NonBlockingArrayStrategy(int operation) {
        this.operation = operation;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Nothing to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        VolatileArrayAccessEvent event = new VolatileArrayAccessEvent(context.object());
        event.setArrayIndex(context.intParam());
        event.setOperation(operation);

        ExecuteRunAfter<VolatileArrayAccessEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(event);

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
