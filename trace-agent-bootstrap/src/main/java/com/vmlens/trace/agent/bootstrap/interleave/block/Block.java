package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainerBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;

/**
 * if we have a single element, start and end are the same
 */
public class Block implements WithThreadIndex  {
    private final BlockElement start;
    private final BlockElement end;
    public Block(BlockElement start, BlockElement end) {
        this.start = start;
        this.end = end;
    }
    @Override
    public int threadIndex() {return start.threadIndex();}

    public void addToAlternatingOrderContainerBuilder(Block blockFromOtherThread,
                                                      AlternatingOrderContainerBuilder alternatingOrderContainerBuilder) {
         if(end.startsAlternatingOrder(blockFromOtherThread.start)) {
             alternatingOrderContainerBuilder.addAlternatingOrder(new AlternatingOrderElement(
                     new LeftBeforeRight(end.position(), blockFromOtherThread.start.position()),
                     new LeftBeforeRight(blockFromOtherThread.end.position(), start.position())));
         }
        if(end.startsFixedOrder(blockFromOtherThread.start)) {
            alternatingOrderContainerBuilder.addFixedOrder(
                    new LeftBeforeRight(end.position(), blockFromOtherThread.start.position()));
        }

    }
}
