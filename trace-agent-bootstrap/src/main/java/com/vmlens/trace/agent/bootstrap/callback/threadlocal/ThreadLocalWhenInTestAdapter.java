package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public interface ThreadLocalWhenInTestAdapter {

    boolean process(CallbackAction callbackAction);

    void join(Object taskOrPool);

    ThreadLocalForParallelize threadLocalForParallelize();

    QueueIn eventQueue();


}
