package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class OnAfterMethodCall implements CallbackAction {

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
