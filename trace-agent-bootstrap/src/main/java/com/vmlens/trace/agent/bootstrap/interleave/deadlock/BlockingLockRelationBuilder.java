package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnter;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;

/**
 * creates the lock relation by using the currently active locks
 *
 */

public class BlockingLockRelationBuilder {

    private final THashMap<LockPair,LockPairCombinationAndThreadIndices> map = new THashMap<>();
    private final ActiveLockCollection activeLockCollection = new ActiveLockCollection();

    public void onLockEnter(ElementAndPosition<LockEnter> enter) {
        activeLockCollection.push(enter);
        fill(activeLockCollection.listAt(enter.threadIndex()));
    }

    public void onLockExit(int threadIndex,  LockKey forLockOrMonitor) {
        activeLockCollection.pop(threadIndex,forLockOrMonitor);
    }

    public BlockingLockRelation build() {
        return new BlockingLockRelation(map);
    }

    private void fill(TLinkedList<TLinkableWrapper<ElementAndPosition<LockEnter>>> list) {
        for(int i = 0 ; i < list.size(); i++) {
            TLinkableWrapper<ElementAndPosition<LockEnter>> parent = list.get(i);
            for(int j = i + 1 ; j <  list.size(); j++ ) {
                TLinkableWrapper<ElementAndPosition<LockEnter>> child = list.get(j);
                add(parent.element(),child.element());
            }
        }
    }

    private void add(ElementAndPosition<LockEnter> parent,ElementAndPosition<LockEnter> child ) {
        PositionPair positionPair = new PositionPair(parent.position(),child.position());
        LockPair pair = new LockPair(parent.element().key(), child.element().key());
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
