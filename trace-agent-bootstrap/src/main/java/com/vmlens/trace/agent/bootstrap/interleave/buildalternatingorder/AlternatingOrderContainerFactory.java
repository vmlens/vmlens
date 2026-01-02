package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderContainerFactory {

    private final InterleaveLoopContext interleaveLoopContext;

    public AlternatingOrderContainerFactory(InterleaveLoopContext interleaveLoopContext) {
        this.interleaveLoopContext = interleaveLoopContext;
    }

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun) {
        Pair<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>, ThreadIndexToElementList<Position>>
                interleaveActionWitPositionAndRun = new InterleaveActionWithPositionFactory().create(actualRun);
        KeyToOperationCollection collection = createCollection(interleaveActionWitPositionAndRun.getLeft());
        BuildAlternatingOrderContext context = new BuildAlternatingOrderContext(
                interleaveActionWitPositionAndRun.getRight(),collection.buildDeadLockMap());
        return new AlternatingOrderContainer(
                interleaveActionWitPositionAndRun.getRight(),
                collection.buildFixedOrders(interleaveActionWitPositionAndRun.getRight()),
                collection.buildOrderTree(context),
                interleaveLoopContext);
    }

    public KeyToOperationCollection createCollection(
            TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> actualRun) {
        ThreadIndexToElementList<ElementAndPosition<InterleaveAction>> threadIndexToElementList = new
                ThreadIndexToElementList<>();
        for (TLinkableWrapper<ElementAndPosition<InterleaveAction>> blockBuilder : actualRun) {
            threadIndexToElementList.add(blockBuilder.element());
        }
        KeyToOperationCollection result = new KeyToOperationCollection(interleaveLoopContext);
        ActiveLockCollection mapContainingStack = new ActiveLockCollection();
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>> thread : threadIndexToElementList) {
            for (TLinkableWrapper<ElementAndPosition<InterleaveAction>> current : thread.element()) {
                current.element().element().addToKeyToOperationCollection(current.element().position(), mapContainingStack, result);
            }
        }
        result.setDeadlocks(mapContainingStack.build().build());
        return result;
    }
}
