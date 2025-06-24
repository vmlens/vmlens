package com.vmlens.trace.agent.bootstrap.interleave.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelation;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.ListIterator;

public class ActiveLockCollection {

    private final BlockingLockRelationBuilder blockingLockRelationBuilder = new BlockingLockRelationBuilder();
    private final ThreadIndexToElementList<ElementAndPosition<LockEnterOrTryLock>> threadIndexToLockList =
            new ThreadIndexToElementList<>();


    public void push(ElementAndPosition<LockEnterOrTryLock> enter) {
        threadIndexToLockList.add(enter);
        blockingLockRelationBuilder.fill(threadIndexToLockList.listAt(enter.threadIndex()));
    }

    public ElementAndPosition<LockEnterOrTryLock> pop(int threadIndex, LockKey forLockOrMonitor) {
        TLinkedList<TLinkableWrapper<ElementAndPosition<LockEnterOrTryLock>>> list = threadIndexToLockList.listAt(threadIndex);
        if(list == null) {
            return null;
        }
        ListIterator<TLinkableWrapper<ElementAndPosition<LockEnterOrTryLock>>> iter = list.listIterator(list.size() );
        while(iter.hasPrevious()) {
            TLinkableWrapper<ElementAndPosition<LockEnterOrTryLock>> element = iter.previous();
            if(element.element().element().key().equals(forLockOrMonitor)) {
                iter.remove();
                return element.element();
            }
        }
        return null;
    }

    public TLinkedList<TLinkableWrapper<ElementAndPosition<LockEnterOrTryLock>>> listAt(int threadIndex) {
        return threadIndexToLockList.listAt(threadIndex);
    }

    public BlockingLockRelation build() {
        return blockingLockRelationBuilder.build();
    }
}
