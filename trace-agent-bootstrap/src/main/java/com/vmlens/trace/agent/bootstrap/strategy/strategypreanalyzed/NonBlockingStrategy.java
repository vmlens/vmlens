package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicNonBlockingEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NonBlockingStrategy extends StrategyWithoutParam {

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

        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }

}
