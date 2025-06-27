package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelation;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.ListIterator;

public class ActiveLockCollection {

    private final BlockingLockRelationBuilder blockingLockRelationBuilder = new BlockingLockRelationBuilder();
    private final ThreadIndexToElementList<LockStartOperation> threadIndexToLockList =
            new ThreadIndexToElementList<>();


    public void push(LockStartOperation enter) {
        threadIndexToLockList.add(enter);
        blockingLockRelationBuilder.fill(threadIndexToLockList.listAt(enter.threadIndex()));
    }

    public LockStartOperation pop(int threadIndex, LockKey forLockOrMonitor) {
        TLinkedList<TLinkableWrapper<LockStartOperation>> list = threadIndexToLockList.listAt(threadIndex);
        if(list == null) {
            return null;
        }
        ListIterator<TLinkableWrapper<LockStartOperation>> iter = list.listIterator(list.size() );
        while(iter.hasPrevious()) {
            TLinkableWrapper<LockStartOperation> element = iter.previous();
            if(element.element().key().equals(forLockOrMonitor)) {
                iter.remove();
                return element.element();
            }
        }
        return null;
    }

    public TLinkedList<TLinkableWrapper<LockStartOperation>> listAt(int threadIndex) {
        return threadIndexToLockList.listAt(threadIndex);
    }

    public BlockingLockRelation build() {
        return blockingLockRelationBuilder.build();
    }
}
