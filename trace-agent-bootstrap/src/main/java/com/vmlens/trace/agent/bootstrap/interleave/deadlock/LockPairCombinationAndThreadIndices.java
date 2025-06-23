package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

public class LockPairCombinationAndThreadIndices {

    private final LockPair first;
    private final THashSet<PositionPair> firstPositions;
    private final TIntHashSet threadIndexSet;
    private LockPair second;
    private final THashSet<PositionPair> secondPositions  = new THashSet<>();

    public LockPairCombinationAndThreadIndices(int threadIndex, PositionPair firstPosition,LockPair first) {
        this.first = first;
        this.threadIndexSet = new TIntHashSet();
        threadIndexSet.add(threadIndex);
        this.firstPositions = new THashSet<>();
        firstPositions.add(firstPosition);
    }

    public void add(int threadIndex, PositionPair position, LockPair next) {
        if(! first.equals(next)) {
            second = next;
            secondPositions.add(position);
        } else {
            firstPositions.add(position);
        }
        threadIndexSet.add(threadIndex);
    }

    /**
     *
     * can return null
     *
     */

}
