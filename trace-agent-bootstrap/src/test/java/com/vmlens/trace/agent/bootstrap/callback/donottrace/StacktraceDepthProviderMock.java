package com.vmlens.trace.agent.bootstrap.callback.donottrace;

import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.StacktraceDepthProvider;

public class StacktraceDepthProviderMock implements StacktraceDepthProvider  {

    private final int depth;

    public StacktraceDepthProviderMock(int depth) {
        this.depth = depth;
    }

    @Override
    public int getStacktraceDepth() {
        return depth;
    }
}
