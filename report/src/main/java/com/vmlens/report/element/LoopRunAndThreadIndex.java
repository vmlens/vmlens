package com.vmlens.report.element;

public class LoopRunAndThreadIndex {

    private final int loopId;
    private final int runId;
    private final int threadIndex;

    public LoopRunAndThreadIndex(int loopId, int runId, int threadIndex) {
        this.loopId = loopId;
        this.runId = runId;
        this.threadIndex = threadIndex;
    }

    public int runId() {
        return runId;
    }

    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoopRunAndThreadIndex that = (LoopRunAndThreadIndex) o;
        return loopId == that.loopId && runId == that.runId && threadIndex == that.threadIndex;
    }

    @Override
    public int hashCode() {
        int result = loopId;
        result = 31 * result + runId;
        result = 31 * result + threadIndex;
        return result;
    }

    @Override
    public String toString() {
        return "LoopRunAndThreadIndex{" +
                "loopId=" + loopId +
                ", runId=" + runId +
                ", threadIndex=" + threadIndex +
                '}';
    }
}
