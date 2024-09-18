package com.vmlens.trace.agent.bootstrap.event;

public class LoopIdAndRunId {
    private final int loopId;
    private final int runId;

    public LoopIdAndRunId(int loopId, int runId) {
        this.loopId = loopId;
        this.runId = runId;
    }

    public int loopId() {
        return loopId;
    }

    public int runId() {
        return runId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopIdAndRunId that = (LoopIdAndRunId) o;

        if (loopId != that.loopId) return false;
        return runId == that.runId;
    }

    @Override
    public int hashCode() {
        int result = loopId;
        result = 31 * result + runId;
        return result;
    }
}
