package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class PositionTestBuilder {

    private int threadIndex;
    private int positionInThread;

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setPositionInThread(int positionInThread) {
        this.positionInThread = positionInThread;
    }

    public Position build() {
        return new Position(threadIndex, positionInThread);
    }

}
