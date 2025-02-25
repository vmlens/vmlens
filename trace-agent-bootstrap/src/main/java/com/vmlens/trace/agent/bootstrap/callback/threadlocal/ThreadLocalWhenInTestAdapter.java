package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public interface ThreadLocalWhenInTestAdapter {
    void process(CallbackAction callbackAction);

    ThreadLocalForParallelize threadLocalForParallelize();

    QueueIn eventQueue();


}
