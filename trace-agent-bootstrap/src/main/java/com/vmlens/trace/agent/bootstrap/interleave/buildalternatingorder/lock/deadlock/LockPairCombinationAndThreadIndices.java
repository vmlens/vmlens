package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.DeadlockOperation;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

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
    public TLinkedList<TLinkableWrapper<DeadlockOperation>> build()  {
        if(second == null) {
            return null;
        }
        if( threadIndexSet.size() < 2) {
            return null;
        }
        TLinkedList<TLinkableWrapper<DeadlockOperation>> dependentBlockList = new TLinkedList<>();
        for(PositionPair first : firstPositions) {
            for(PositionPair second : secondPositions) {
                if(first.threadIndex() != second.threadIndex()) {
                    dependentBlockList.add(wrap(new DeadlockOperationImpl(first,second)));
                }
            }

        }
        return dependentBlockList;
    }
}
