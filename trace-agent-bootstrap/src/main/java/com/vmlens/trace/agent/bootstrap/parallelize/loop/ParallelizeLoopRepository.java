package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.AgentLogger;
import gnu.trove.map.hash.THashMap;

public class ParallelizeLoopRepository {
    private final ParallelizeLoopFactory parallelizeLoopFactory;
    private final THashMap<Object, ParallelizeLoop> object2ParallelizeLoop = new THashMap<Object, ParallelizeLoop>();
    private int maxLoopId = 0;

    public ParallelizeLoopRepository(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopFactory = parallelizeLoopFactory;
    }

    public ParallelizeLoop getOrCreate(Object config) {
        ParallelizeLoop parallelizeLoop = object2ParallelizeLoop.get(config);
        if(parallelizeLoop == null)  {
            parallelizeLoop = parallelizeLoopFactory.create(maxLoopId);
            maxLoopId++;
            object2ParallelizeLoop.put(config,parallelizeLoop);
        }
        return parallelizeLoop;
    }

    public AgentLogger agentLogger() {
        return parallelizeLoopFactory.agentLogger();
    }
}
