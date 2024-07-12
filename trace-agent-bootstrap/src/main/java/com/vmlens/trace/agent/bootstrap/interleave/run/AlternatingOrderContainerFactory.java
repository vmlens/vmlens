package com.vmlens.trace.agent.bootstrap.interleave.run;

public class AlternatingOrderContainerFactory {

    /*
    Fixme
    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveFactory>> actualRun) {
        BlockBuilderAndCalculatedRunElementContainer container = new BlockBuilderAndCalculatedRunElementContainerFactory().create(actualRun);
        return create(container.runWithPosition, container.run);
    }


    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList,
                                            ThreadIndexToElementList<Position> run) {
        return new AlternatingOrderContainer(new OrderArraysFactory().create(blockBuilderList, run), run);
    }

    public OrderArrays create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList,
                              ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        MapOfBlocks blockMap =
                new MapOfBlocksExceptDeadlockFactory().create(blockBuilderList);
        return create(blockMap, threadIndexToMaxPosition);
    }

     */
}
