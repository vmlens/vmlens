package com.vmlens.trace.agent.bootstrap.parallelize.threadlocal;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;

public interface CallbackAction {

    void execute(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, QueueIn queueIn);

}
