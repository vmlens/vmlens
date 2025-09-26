package com.vmlens.trace.agent.bootstrap.callback.intestaction;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public abstract class AbstractInTestAction implements InTestAction {

    @Override
    public void beforeProcessCheck(ThreadLocalWhenInTest threadLocalDataWhenInTest, StacktraceDepthProvider stacktraceDepthProvider) {

    }
}
