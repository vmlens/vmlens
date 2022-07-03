package com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl;


import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.BuildBlockListContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.DeadlockContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.OrderElementFactory;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.OrderElementFactoryAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock.SingleElementBlock;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class VolatileFieldAccess implements OrderElementFactory<VolatileFieldAccess>, SyncAction {

    public static final int MIN_OPERATION = 3;

    private final int fieldId;
    private final int operation;

    public VolatileFieldAccess(int fieldId, int operation) {
        super();
        this.operation = operation;
        this.fieldId = fieldId;
    }


    @Override
    public String toString() {
        return "VolatileFieldAccess{" +
                "fieldId=" + fieldId +
                ", operation=" + operation +
                '}';
    }

    public void createOrder(Position myPosition, OrderElementFactoryAndPosition<VolatileFieldAccess> other,
                            DeadlockContext deadlockContext,
                            TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                            TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        if (fieldId == other.orderElementFactory.fieldId && (operation | other.orderElementFactory.operation) >= MIN_OPERATION) {
            alternatingOrderElements.add(new TLinkableWrapper<AlternatingOrderElement>(
                    new AlternatingOrderElement(new LeftBeforeRight(myPosition, other.position))));
        }
    }


    @Override
    public void createBlock(Position position, boolean moreThanOneThread,
                            BuildBlockListContext buildBlockListContext,
                            BlockListCollection blockListCollection) {
        OrderElementFactoryAndPosition<VolatileFieldAccess> sap = new OrderElementFactoryAndPosition(moreThanOneThread, position, this);
        blockListCollection.volatileFieldAccess.add(new SingleElementBlock(sap));
    }
}
