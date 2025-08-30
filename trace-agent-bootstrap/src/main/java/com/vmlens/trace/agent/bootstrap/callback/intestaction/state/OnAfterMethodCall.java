package com.vmlens.trace.agent.bootstrap.callback.intestaction.state;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class OnAfterMethodCall implements InTestAction {

    private final int inMethodId;
    private final int position;
    private final ReadWriteLockMap readWriteLockMap;
    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

    public OnAfterMethodCall(int inMethodId,
                             int position,
                             ReadWriteLockMap readWriteLockMap) {
        this.inMethodId = inMethodId;
        this.position = position;
        this.readWriteLockMap = readWriteLockMap;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        if (threadLocalDataWhenInTest.executeAfterOperation() == null) {
            return;
        }

        threadLocalDataWhenInTest.executeAfterOperation().execute(inMethodId, position, threadLocalDataWhenInTest,queueIn,
                readWriteLockMap);
        threadLocalDataWhenInTest.setExecuteAfterOperation(null);
    }

    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }
}
