package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;

public interface CallbackAction {

    void execute(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, QueueIn queueIn);

}
