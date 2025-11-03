package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public interface InTestAction {

    boolean checkOrSetDoNotTrace(ThreadLocalWhenInTest threadLocalDataWhenInTest, 
                                 StacktraceDepthProvider stacktraceDepthProvider);

    void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                 QueueIn queueIn);

    boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
