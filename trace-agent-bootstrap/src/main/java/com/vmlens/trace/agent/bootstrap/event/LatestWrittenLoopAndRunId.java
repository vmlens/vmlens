package com.vmlens.trace.agent.bootstrap.event;

public class LatestWrittenLoopAndRunId {

    private int loopId;
    private int runId;

    public int runId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public int loopId() {
        return loopId;
    }

    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }
}
