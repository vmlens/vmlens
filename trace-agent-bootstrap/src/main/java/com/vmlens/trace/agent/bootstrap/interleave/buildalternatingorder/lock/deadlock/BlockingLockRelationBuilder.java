package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.LockStartOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

/**
 * creates the lock relation by using the currently active locks
 *
 */

public class BlockingLockRelationBuilder {

    private final THashMap<LockPair,LockPairCombinationAndThreadIndices> map = new THashMap<>();



    public BlockingLockRelation build() {
        return new BlockingLockRelation(map);
    }

    public void fill(TLinkedList<TLinkableWrapper<LockStartOperation>> list) {
        for(int i = 0 ; i < list.size(); i++) {
            TLinkableWrapper<LockStartOperation> parent = list.get(i);
            for(int j = i + 1 ; j <  list.size(); j++ ) {
                TLinkableWrapper<LockStartOperation> child = list.get(j);
                add(parent.element(),child.element());
            }
        }
    }

    private void add(LockStartOperation parent, LockStartOperation child ) {
        LockKey parentKey = parent.key();
        LockKey childKey = child.key();

        // if both keys are read they can not create a deadlock
        if(parentKey.isRead() && childKey.isRead()) {
            return;
        }
        if(! parent.canBeDeadlockParent()) {
            return;
        }
        if(! child.canBeDeadlockChild()) {
            return;
        }

        PositionPair positionPair = new PositionPair(parent.position(),child.position());
        LockPair pair = new LockPair(parent,child);
        LockPair normalized = pair.normalized();
        LockPairCombinationAndThreadIndices list = map.get(normalized);
        if(list == null) {
             list = new LockPairCombinationAndThreadIndices(parent.threadIndex(),positionPair,pair);
            map.put(normalized,list);
            return;
        }
        list.add(parent.threadIndex(),positionPair,pair);
    }
}
