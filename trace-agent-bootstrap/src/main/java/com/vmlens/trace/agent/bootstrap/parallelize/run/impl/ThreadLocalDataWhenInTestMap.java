package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import gnu.trove.map.hash.TLongObjectHashMap;


public class ThreadLocalDataWhenInTestMap {

    private final int runId;
    private final int loopId;
    private int maxThreadIndex;

    public ThreadLocalDataWhenInTestMap(int loopId, int runId) {
        this.runId = runId;
        this.loopId = loopId;
    }

    // Package Visible for Test
    final TLongObjectHashMap<ThreadLocalDataWhenInTest> threadIdToState = new TLongObjectHashMap<>();


    public ThreadLocalDataWhenInTest create(Run run, QueueIn queueIn, long threadId) {
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(run, maxThreadIndex,
                queueIn, threadId);
        threadIdToState.put(threadId, threadLocalDataWhenInTest);
        maxThreadIndex++;
        return threadLocalDataWhenInTest;
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
