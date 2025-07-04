package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeafteroperation.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class SetExecuteAfterOperation implements CallbackAction {

    private final ExecuteAfterOperation runtimeEventAndSetInMethodIdAndPosition;
    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

    public SetExecuteAfterOperation(ExecuteAfterOperation
                                                    runtimeEventAndSetInMethodIdAndPosition) {
        this.runtimeEventAndSetInMethodIdAndPosition = runtimeEventAndSetInMethodIdAndPosition;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        threadLocalDataWhenInTest.setExecuteAfterOperation(runtimeEventAndSetInMethodIdAndPosition);
    }

    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }
}
