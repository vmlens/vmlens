package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.FixedAndAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.BlockList;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.DeadlockContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.Either;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.SingleElementBlock;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public class BlockListCollection {

    public final BlockList<SingleElementBlock<VolatileFieldAccess>> volatileFieldAccess = new
            BlockList<SingleElementBlock<VolatileFieldAccess>>();
    public final BlockList<SingleElementBlock<Either<ThreadBegin, ThreadStart>>> threadStartAndBegin = new
            BlockList<SingleElementBlock<Either<ThreadBegin, ThreadStart>>>();
    public final BlockList<SingleElementBlock<Either<ThreadEnd, ThreadJoin>>> threadJoinAndEnd = new
            BlockList<SingleElementBlock<Either<ThreadEnd, ThreadJoin>>>();
    
    
    public FixedAndAlternatingOrder createOrder(DeadlockContext deadlockContext) {
        TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> optionalAlternatingOrderElements =
                new TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>>();
        TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements =
                new TLinkedList<TLinkableWrapper<LeftBeforeRightOld>>();
        volatileFieldAccess.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        threadStartAndBegin.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        threadJoinAndEnd.createOrder(deadlockContext, fixedOrderElements, optionalAlternatingOrderElements);
        return new FixedAndAlternatingOrder(fixedOrderElements.toUnlinkedArray(new TLinkableWrapper[0]),
                optionalAlternatingOrderElements.toUnlinkedArray(new TLinkableWrapper[0]));
    }
}
