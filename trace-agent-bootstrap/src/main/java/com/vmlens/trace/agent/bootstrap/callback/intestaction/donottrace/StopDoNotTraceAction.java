package com.vmlens.trace.agent.bootstrap.callback.intestaction.donottrace;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestAction;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public class StopDoNotTraceAction implements InTestAction  {


    @Override
    public boolean checkOrSetDoNotTrace(ThreadLocalWhenInTest threadLocalDataWhenInTest, StacktraceDepthProvider stacktraceDepthProvider) {
        return false; // threadLocalDataWhenInTest.processAction(stacktraceDepthProvider);
    }

    @Override
    public void execute(ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {

    }

    @Override
    public boolean takeAction(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return false;
    }
}
