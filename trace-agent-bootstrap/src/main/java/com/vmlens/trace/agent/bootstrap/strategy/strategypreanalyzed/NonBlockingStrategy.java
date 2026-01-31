package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicNonBlockingEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NonBlockingStrategy extends StrategyWithoutParam {

    public static final NonBlockingStrategy NON_BLOCKING_READ = new NonBlockingStrategy(MemoryAccessType.IS_READ);
    public static final NonBlockingStrategy NON_BLOCKING_WRITE = new NonBlockingStrategy(MemoryAccessType.IS_WRITE);
    public static final NonBlockingStrategy NON_BLOCKING_READ_WRITE = new NonBlockingStrategy(MemoryAccessType.IS_READ_WRITE);



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
