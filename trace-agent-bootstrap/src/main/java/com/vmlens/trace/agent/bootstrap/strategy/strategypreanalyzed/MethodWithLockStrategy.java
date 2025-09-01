package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicReadWriteLockEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicReadWriteLockExitEvent;
import com.vmlens.trace.agent.bootstrap.lock.ReadOrWriteLock;

public class MethodWithLockStrategy implements StrategyPreAnalyzed {

    private final ReadOrWriteLock lockType;

    public MethodWithLockStrategy(ReadOrWriteLock lockType) {
        this.lockType = lockType;
    }

    @Override
    public void methodEnter(EnterExitContext context) {

        AtomicReadWriteLockEnterEvent event = new AtomicReadWriteLockEnterEvent(lockType);
        event.setAtomicMethodId(context.methodId());

        RunAfter<AtomicReadWriteLockEnterEvent> action = new
                RunAfter<>(event,
                new SetInMethodIdPositionObjectHashCode<>(context.object()));

        context.threadLocalWhenInTestAdapter().process(action);
    }

    @Override
    public void methodExit(EnterExitContext context) {
        AtomicReadWriteLockExitEvent event = new AtomicReadWriteLockExitEvent(lockType, context.object());
        event.setAtomicMethodId(context.methodId());

        ExecuteRunAfter<AtomicReadWriteLockExitEvent> runtimeEventAndSetInMethodIdAndPositionImpl =
                new ExecuteRunAfter<>(event);
        context.threadLocalWhenInTestAdapter().process(
                new SetExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPositionImpl));
    }

    @Override
    public void beforeMethodCall(BeforeAfterContext beforeAfterContext) {
        // Fixme same as before at all -> problem multiple
        // method calls requires stack?  e.g. how to store the method id and position?
    }

    @Override
    public void afterMethodCall(BeforeAfterContext beforeAfterContext) {
        // Fixme same as before at all -> problem multiple
        // method calls requires stack? -> e.g. how to store the method id and position?
    }
}
