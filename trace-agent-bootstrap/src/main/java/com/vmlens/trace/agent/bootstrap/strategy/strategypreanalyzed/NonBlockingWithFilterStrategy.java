package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.FilterActionEnd;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.ExecuteFilterActionBegin;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicNonBlockingEvent;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;

public class NonBlockingWithFilterStrategy extends StrategyWithoutParam  {

    public static final NonBlockingWithFilterStrategy NON_BLOCKING_WITH_FILTER_READ = new NonBlockingWithFilterStrategy(MemoryAccessType.IS_READ);
    public static final NonBlockingWithFilterStrategy NON_BLOCKING_WITH_FILTER_WRITE = new NonBlockingWithFilterStrategy(MemoryAccessType.IS_WRITE);
    public static final NonBlockingWithFilterStrategy NON_BLOCKING_WITH_FILTER_READ_WRITE = new NonBlockingWithFilterStrategy(MemoryAccessType.IS_READ_WRITE);



    private final int operation;

    public NonBlockingWithFilterStrategy(int operation) {
        this.operation = operation;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        context.inTestActionProcessor().process(new ExecuteFilterActionBegin());
    }

    @Override
    public void methodExit(EnterExitContext context) {
        AtomicNonBlockingEvent event = new AtomicNonBlockingEvent(operation, context.object());
        event.setAtomicMethodId(context.methodId());

        ExecuteRunAfter<AtomicNonBlockingEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(event);
        context.inTestActionProcessor().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl
                        , new FilterActionEnd()));
    }

}
