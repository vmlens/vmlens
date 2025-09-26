package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public interface InTestAction {

    void beforeProcessCheck(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                            StacktraceDepthProvider stacktraceDepthProvider);

    void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                 QueueIn queueIn);

    boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
