package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import gnu.trove.map.hash.TLongObjectHashMap;

public class RunContext {

    private final int runId;
    private final int loopId;

    public RunContext(int loopId, int runId) {
        this.runId = runId;
        this.loopId = loopId;
    }

    // Package Visible for Test
    final TLongObjectHashMap<ThreadLocalDataWhenInTest> threadIdToState = new TLongObjectHashMap<>();

    public void add(ThreadLocalDataWhenInTest testThreadState) {
        threadIdToState.put(testThreadState.threadId(), testThreadState);
    }

    public int threadIndexForThreadId(long id) {
        return threadIdToState.get(id).threadIndex();
    }

    public int runId() {
        return runId;
    }

    public int loopId() {
        return loopId;
    }
}
