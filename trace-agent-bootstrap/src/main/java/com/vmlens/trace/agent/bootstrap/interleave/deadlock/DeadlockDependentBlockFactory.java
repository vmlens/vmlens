package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.KeyToThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class DeadlockDependentBlockFactory {

    public KeyToThreadIdToElementList<Object, DependentBlock> create(TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>
                                                            actualRun) {
        BlockingLockRelationBuilder blockingLockRelationBuilder = new BlockingLockRelationBuilder();
        Iterator<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> iter = actualRun.iterator();
        while(iter.hasNext()) {
            ElementAndPosition<InterleaveAction> element = iter.next().element();
            element.element().addToBlockingLockRelationBuilder(element.position(),blockingLockRelationBuilder);
        }
        return blockingLockRelationBuilder.build().build();
    }

}
