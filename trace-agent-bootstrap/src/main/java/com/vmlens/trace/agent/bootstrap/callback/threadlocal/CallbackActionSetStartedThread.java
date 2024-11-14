package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class CallbackActionSetStartedThread implements CallbackAction {

    private final RunnableOrThreadWrapper startedThread;

    public CallbackActionSetStartedThread(RunnableOrThreadWrapper startedThread) {
        this.startedThread = startedThread;
    }

    @Override
    public void execute(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        threadLocalDataWhenInTest.setStartedThread(startedThread);
    }
}
