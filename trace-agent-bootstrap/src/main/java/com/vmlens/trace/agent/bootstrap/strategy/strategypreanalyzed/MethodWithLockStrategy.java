package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.SetExecuteAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicReadWriteLockEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.AtomicReadWriteLockExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.LockExitEvent;
import com.vmlens.trace.agent.bootstrap.lock.LockEvent;
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
                new SetExecuteAfterMethodCall(runtimeEventAndSetInMethodIdAndPositionImpl));
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
