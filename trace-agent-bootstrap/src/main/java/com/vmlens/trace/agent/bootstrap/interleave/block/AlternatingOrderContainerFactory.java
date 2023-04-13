package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainerBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunElement;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class AlternatingOrderContainerFactory {

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun) {
        TLinkedList<TLinkableWrapper<InterleaveActionWithPosition>> runWithPosition = new TLinkedList<>();

        ThreadIdToElementList<CalculatedRunElement> run = new ThreadIdToElementList<>();
        for(TLinkableWrapper<InterleaveActionWithPositionFactory> syncAction : actualRun) {
            InterleaveActionWithPosition withPosition = syncAction.element
                    .create(run.getPositionAtThreadIndex(syncAction.element.threadIndex()));
            run.add(withPosition);
            runWithPosition.add(new TLinkableWrapper(withPosition));
        }

        KeyToThreadIdToElementList<BlockKey, Block> blockMap =
                new BlockMapFactory().create(runWithPosition);

        return create(blockMap,run);
    }

    private AlternatingOrderContainer create(KeyToThreadIdToElementList<BlockKey, Block> blockMap,
                                            ThreadIdToElementList<CalculatedRunElement> actualRun) {
        AlternatingOrderContainerBuilder builder = new AlternatingOrderContainerBuilder();
        for(ThreadIdToElementList<Block> threadIdToElementList : blockMap) {
            for(TLinkableWrapper<TLinkedList<TLinkableWrapper<Block>>> oneThread : threadIdToElementList) {
                for(TLinkableWrapper<Block> current : oneThread.element) {
                    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<Block>>>> otherThreadBlocks =
                            threadIdToElementList.iteratorAt(current.element.threadIndex()+1);
                    while(otherThreadBlocks.hasNext()) {
                        TLinkedList<TLinkableWrapper<Block>> otherThread = otherThreadBlocks.next().element;
                        // ToDo we schould probably stop when more than n elements were created
                        for(TLinkableWrapper<Block> otherBlock : otherThread) {
                            current.element.addToAlternatingOrderContainerBuilder(otherBlock.element,builder);
                        }
                    }
                }
            }
        }
        return builder.build(actualRun);
    }
}
