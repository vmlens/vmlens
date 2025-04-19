package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class RunNewTestTaskStarted implements CallbackAction {

    private final RunnableOrThreadWrapper newThread;

    public RunNewTestTaskStarted(RunnableOrThreadWrapper newThread) {
        this.newThread = newThread;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().newTestTaskStarted(newThread);
    }
}
