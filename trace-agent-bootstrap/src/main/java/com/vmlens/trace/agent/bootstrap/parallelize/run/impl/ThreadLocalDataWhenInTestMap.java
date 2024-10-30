package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ThreadLocalDataWhenInTest;
import gnu.trove.map.hash.TLongObjectHashMap;


public class ThreadLocalDataWhenInTestMap {
    private int maxThreadIndex;
    // Package Visible for Test
    final TLongObjectHashMap<ThreadLocalDataWhenInTest> threadIdToState = new TLongObjectHashMap<>();

    public ThreadLocalDataWhenInTest createForMainTestThread(Run run, long threadId) {
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(run, maxThreadIndex);
        threadIdToState.put(threadId, threadLocalDataWhenInTest);
        maxThreadIndex++;
        return threadLocalDataWhenInTest;
    }
    public ThreadLocalDataWhenInTest createForStartedThread(Run run, long threadId, int newThreadIndex) {
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = new ThreadLocalDataWhenInTest(run, newThreadIndex);
        threadIdToState.put(threadId, threadLocalDataWhenInTest);
        return threadLocalDataWhenInTest;
    }
    int getThreadIndexForNewTestThread() {
        int temp = maxThreadIndex;
        maxThreadIndex++;
        return temp;
    }
}
