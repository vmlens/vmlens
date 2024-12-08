package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import gnu.trove.map.hash.TLongObjectHashMap;


public class ThreadLocalDataWhenInTestMap {
    private int maxThreadIndex;
    // Package Visible for Test
    final TLongObjectHashMap<ThreadLocalWhenInTest> threadIdToState = new TLongObjectHashMap<>();

    public ThreadLocalWhenInTest createForMainTestThread(Run run, long threadId) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, maxThreadIndex);
        threadIdToState.put(threadId, threadLocalDataWhenInTest);
        maxThreadIndex++;
        return threadLocalDataWhenInTest;
    }

    public ThreadLocalWhenInTest createForStartedThread(Run run, long threadId, int newThreadIndex) {
        ThreadLocalWhenInTest threadLocalDataWhenInTest = new ThreadLocalWhenInTest(run, newThreadIndex);
        threadIdToState.put(threadId, threadLocalDataWhenInTest);
        return threadLocalDataWhenInTest;
    }

    public int getThreadIndexForNewTestThread() {
        int temp = maxThreadIndex;
        maxThreadIndex++;
        return temp;
    }
}
