package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.apache.commons.lang3.tuple.Pair;

public class AlternatingOrderContainerFactory {

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun) {
        Pair<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>, ThreadIndexToElementList<Position>>
                interleaveActionWitPositionAndRun = new InterleaveActionWithPositionFactory().create(actualRun);

        MapOfBlocks mapOfBlocksExceptDeadlock = new MapOfBlocksExceptDeadlockFactory().create(
                interleaveActionWitPositionAndRun.getLeft());

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();

        new AddDependentBlocksToOrderArraysBuilder().add(mapOfBlocksExceptDeadlock.dependentBlocks(), orderArraysBuilder);
        new AddIndependentBlocksToOrderArraysBuilder().add(mapOfBlocksExceptDeadlock.inDependentBlocks(),
                interleaveActionWitPositionAndRun.getRight(),
                orderArraysBuilder);

        return new AlternatingOrderContainer(orderArraysBuilder.build(), interleaveActionWitPositionAndRun.getRight());
    }
}
