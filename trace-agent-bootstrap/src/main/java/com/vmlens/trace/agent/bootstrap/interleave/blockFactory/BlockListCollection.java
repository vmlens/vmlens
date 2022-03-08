package com.vmlens.trace.agent.bootstrap.interleave.blockFactory;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.FixedAndAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.BlockList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.DeadlockContext;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.Either;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory.SingleElementBlock;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * Fixme interface nach orderFactory und in interleave odr syncaction
 */
public class BlockListCollection {

    public final BlockList<SingleElementBlock<VolatileFieldAccess>> volatileFieldAccess = new
            BlockList<SingleElementBlock<VolatileFieldAccess>>();
    public final BlockList<SingleElementBlock<Either<ThreadBegin, ThreadStart>>> threadStartAndBegin = new
            BlockList<SingleElementBlock<Either<ThreadBegin, ThreadStart>>>();
    public final BlockList<SingleElementBlock<Either<ThreadEnd, ThreadJoin>>> threadJoinAndEnd = new
            BlockList<SingleElementBlock<Either<ThreadEnd, ThreadJoin>>>();
    
    
    public FixedAndAlternatingOrder createOrder(DeadlockContext deadlockContext) {
        TLinkedList<TLinkableWrapper<AlternatingOrderElement>> optionalAlternatingOrderElements =
                new TLinkedList<TLinkableWrapper<AlternatingOrderElement>>();
        TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements =
                new TLinkedList<TLinkableWrapper<LeftBeforeRight>>();
        volatileFieldAccess.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        threadStartAndBegin.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        threadJoinAndEnd.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        return new FixedAndAlternatingOrder(fixedOrderElements.toUnlinkedArray(new TLinkableWrapper[0]),
                optionalAlternatingOrderElements.toUnlinkedArray(new TLinkableWrapper[0]));
    }
}
