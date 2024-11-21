package com.vmlens.trace.agent.bootstrap.callback.callbackaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;

public interface CallbackAction {

    void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn);

}
