package com.vmlens.trace.agent.bootstrap.parallelize;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsLoop;
import gnu.trove.map.hash.THashMap;

/**
 * Responsible for getOrCreate of AllInterleavingsLoops
 */

public class AllInterleavingsLoopCollection {

    private final THashMap<AllInterleavings, AllInterleavingsLoop> object2InterleaveMultipleRuns = new THashMap<AllInterleavings, AllInterleavingsLoop>();
    private final Object MONITOR = new Object();
    private int maxLoopId = 0;

    AllInterleavingsLoop getOrCreate(AllInterleavings allInterleavings) {
        synchronized (MONITOR) {
            AllInterleavingsLoop loop = object2InterleaveMultipleRuns.get(allInterleavings);
            if (loop == null) {
                loop = new AllInterleavingsLoop(allInterleavings, maxLoopId);
                maxLoopId++;
                object2InterleaveMultipleRuns.put(allInterleavings, loop);
            }
            return loop;
        }
    }

    void beginThread() {

    }

}