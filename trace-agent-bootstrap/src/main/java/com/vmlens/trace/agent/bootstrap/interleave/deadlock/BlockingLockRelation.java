package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.KeyToThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
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

    public KeyToThreadIdToElementList<Object, DependentBlock> build() {
        KeyToThreadIdToElementList<Object, DependentBlock> result = new KeyToThreadIdToElementList<>();
        // we go through all lock pair combination
        Iterator<Map.Entry<LockPair, LockPairCombinationAndThreadIndices>> iterator =
                map.entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry<LockPair, LockPairCombinationAndThreadIndices> element = iterator.next();
            ThreadIndexToElementList<DependentBlock> dependentBlocks = element.getValue().build();
            if(dependentBlocks != null) {
                result.putThreadIndexToElementList(element.getKey(),dependentBlocks);
            }
        }
        return result;
    }
}
