package com.vmlens.trace.agent.bootstrap.callback.intestaction.donottrace;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public class StartDoNotTraceAction implements InTestAction {
    @Override
    public void beforeProcessCheck(ThreadLocalWhenInTest threadLocalDataWhenInTest, StacktraceDepthProvider stacktraceDepthProvider) {
        threadLocalDataWhenInTest.startDoNotTrace(stacktraceDepthProvider);
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {

    }

    @Override
    public boolean notInAtomicCallback(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return false;
    }
}
