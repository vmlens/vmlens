package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
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
     * @return
     */
    public ThreadIndexToElementList<DependentBlock> build()  {
        if(second == null) {
            return null;
        }
        if( threadIndexSet.size() < 2) {
            return null;
        }
        ThreadIndexToElementList<DependentBlock> dependentBlockList = new ThreadIndexToElementList<>();
        for(PositionPair positionPair : firstPositions) {
            positionPair.addToDependentBlockList(first,dependentBlockList);
        }
        for(PositionPair positionPair : secondPositions) {
            positionPair.addToDependentBlockList(second,dependentBlockList);
        }
        return dependentBlockList;
    }
}
