package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import gnu.trove.map.hash.THashMap;

/**
 *
 * all relations between blocking lock enter, to build dependent blocks or deadlocks
 *
 */

public class BlockingLockRelation {

    private final THashMap<LockPair,LockPairCombinationAndThreadIndices> map;

    public BlockingLockRelation(THashMap<LockPair, LockPairCombinationAndThreadIndices> map) {
        this.map = map;
    }


}
