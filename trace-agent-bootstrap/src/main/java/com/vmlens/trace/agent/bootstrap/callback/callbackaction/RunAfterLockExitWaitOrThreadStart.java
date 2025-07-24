package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.NotInAtomicCallbackStrategy;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.notInatomiccallback.WithoutAtomic;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class RunAfterLockExitWaitOrThreadStart implements CallbackAction {

    private final NotInAtomicCallbackStrategy notInAtomicCallbackStrategy = new WithoutAtomic();

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().afterLockExitWaitOrThreadStart(threadLocalDataWhenInTest,queueIn);
    }


    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return notInAtomicCallbackStrategy.notInAtomicCallback(threadLocalDataWhenInTest);
    }

}
