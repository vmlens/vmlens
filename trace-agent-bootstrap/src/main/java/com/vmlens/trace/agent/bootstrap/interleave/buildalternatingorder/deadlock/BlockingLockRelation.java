package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes.DeadlockOperation;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;
import java.util.Map;

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

    public TLinkedList<TLinkableWrapper<DeadlockOperation>> build() {
        TLinkedList<TLinkableWrapper<DeadlockOperation>> result = new TLinkedList<>();
        // we go through all lock pair combination
        Iterator<Map.Entry<LockPair, LockPairCombinationAndThreadIndices>> iterator =
                map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<LockPair, LockPairCombinationAndThreadIndices> element = iterator.next();
            TLinkedList<TLinkableWrapper<DeadlockOperation>> dependentBlocks = element.getValue().build();
            if(dependentBlocks != null) {
                result.addAll(dependentBlocks);
            }
        }
        return result;
    }
}
