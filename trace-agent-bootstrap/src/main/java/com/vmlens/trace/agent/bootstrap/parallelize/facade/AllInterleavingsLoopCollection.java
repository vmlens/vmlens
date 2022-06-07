package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.AllInterleavingsLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRunFactory;
import gnu.trove.map.hash.THashMap;

/**
 * Responsible for getOrCreate of AllInterleavingsLoops
 */

public class AllInterleavingsLoopCollection {

    private final THashMap<AllInterleavings, AllInterleavingsLoop> object2InterleaveMultipleRuns = new THashMap<AllInterleavings, AllInterleavingsLoop>();
    private final Object MONITOR = new Object();
    private final SynchronizationWrapperForRunFactory synchronizationWrapperForRunFactory;
    private int maxLoopId = 0;

    public AllInterleavingsLoopCollection(SynchronizationWrapperForRunFactory synchronizationWrapperForRunFactory) {
        this.synchronizationWrapperForRunFactory = synchronizationWrapperForRunFactory;
    }

    AllInterleavingsLoop getOrCreate(AllInterleavings allInterleavings) {
        synchronized (MONITOR) {
            AllInterleavingsLoop loop = object2InterleaveMultipleRuns.get(allInterleavings);
            if (loop == null) {
                loop = new AllInterleavingsLoop(synchronizationWrapperForRunFactory, allInterleavings, maxLoopId);
                maxLoopId++;
                object2InterleaveMultipleRuns.put(allInterleavings, loop);
            }
            return loop;
        }
    }

    void beginThread() {

    }

}