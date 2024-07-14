package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderContainerFactory<T extends BlockBuilderWithThreadIndex> {

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<T>> actualRun) {
        BlockBuilderAndCalculatedRunElementContainer container = new BlockBuilderWithPositionFactory<T>().create(actualRun);

        MapOfBlocks mapOfBlocksExceptDeadlock = new MapOfBlocksExceptDeadlockFactory().create(container.runWithPosition);

        OrderArraysBuilder orderArraysBuilder = new OrderArraysBuilder();


        new AddDependentBlocksToOrderArraysBuilder().add(mapOfBlocksExceptDeadlock.dependentBlocks(), orderArraysBuilder);
        new AddIndependentBlocksToOrderArraysBuilder().add(mapOfBlocksExceptDeadlock.inDependentBlocks(),
                container.run,
                orderArraysBuilder);

        return new AlternatingOrderContainer(orderArraysBuilder.build(), container.run);
    }
}
