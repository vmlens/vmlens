package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.DeadlockDependentBlockFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Iterator;

public class AlternatingOrderContainerFactory {

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun) {
        Pair<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>, ThreadIndexToElementList<Position>>
                interleaveActionWitPositionAndRun = new InterleaveActionWithPositionFactory().create(actualRun);




        return new AlternatingOrderContainer(orderArraysBuilder.build(), interleaveActionWitPositionAndRun.getRight());
    }

    public KeyToOperationCollection createCollection(
            TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> actualRun) {
        ThreadIndexToElementList<ElementAndPosition<InterleaveAction>> threadIndexToElementList = new
                ThreadIndexToElementList<>();
        for (TLinkableWrapper<ElementAndPosition<InterleaveAction>> blockBuilder : actualRun) {
            threadIndexToElementList.add(blockBuilder.element());
        }
        KeyToOperationCollection result = new KeyToOperationCollection();
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>>> multipleThreadsIterator =
                threadIndexToElementList.iterator();
        while (multipleThreadsIterator.hasNext()) {
            TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>> thread =
                    multipleThreadsIterator.next();
            Iterator<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> perThreadIterator = thread.element().iterator();
            while (perThreadIterator.hasNext()) {
                TLinkableWrapper<ElementAndPosition<InterleaveAction>> current = perThreadIterator.next();
                current.element().element().blockBuilderAdd(current.element().position(), mapContainingStack, result);
            }
        }
        return result;
    }
}
