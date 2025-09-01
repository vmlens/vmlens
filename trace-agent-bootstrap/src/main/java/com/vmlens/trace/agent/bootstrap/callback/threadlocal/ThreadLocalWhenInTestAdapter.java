package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public interface ThreadLocalWhenInTestAdapter {

    boolean process(InTestAction inTestAction);

    void join(Object taskOrPool);

    ThreadLocalForParallelize threadLocalForParallelize();

    QueueIn eventQueue();


}
