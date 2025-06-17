package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public class RunNewTestTaskStarted implements CallbackAction {

    private final ThreadWrapper newThread;

    public RunNewTestTaskStarted(ThreadWrapper newThread) {
        this.newThread = newThread;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                        QueueIn queueIn) {
        threadLocalDataWhenInTest.runAdapter().newTestTaskStarted(newThread);
    }
}
