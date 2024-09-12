package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import gnu.trove.map.hash.THashMap;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

/**
 * Adapter for the generic class @see gnu.trove.map.hash.THashMap
 */
public class ParallelizeLoopRepository {
    private final Object lock = new Object();
    private final ParallelizeLoopFactory parallelizeLoopFactory;
    private final THashMap<Object, ParallelizeLoop> object2ParallelizeLoop = new THashMap<Object, ParallelizeLoop>();
    private int maxLoopId = 0;

    public ParallelizeLoopRepository(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopFactory = parallelizeLoopFactory;
    }

    public Pair<ParallelizeLoop, Boolean> getOrCreate(Object config) {
        synchronized (lock) {
            ParallelizeLoop parallelizeLoop = object2ParallelizeLoop.get(config);
            if (parallelizeLoop == null) {
                parallelizeLoop = parallelizeLoopFactory.create(maxLoopId);
                maxLoopId++;
                object2ParallelizeLoop.put(config, parallelizeLoop);
                return new ImmutablePair(parallelizeLoop, true);
            }
            return new ImmutablePair(parallelizeLoop, false);
        }
    }

}
