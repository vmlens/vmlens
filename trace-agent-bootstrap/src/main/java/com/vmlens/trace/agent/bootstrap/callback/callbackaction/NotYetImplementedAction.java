package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class NotYetImplementedAction implements CallbackAction {

    private final String methodName;

    public NotYetImplementedAction(String methodName) {
        this.methodName = methodName;
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        System.err.println("The method " + methodName + " can currently not be tested with vmlens.");
    }
}
