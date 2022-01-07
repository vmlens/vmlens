package com.vmlens.trace.agent.bootstrap.interleave.domain.syncAction;


import com.vmlens.trace.agent.bootstrap.interleave.domain.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class VolatileFieldAccess implements SyncAction {

    public static final int MIN_OPERATION = 3;

    private final int fieldId;
    private final int operation;

    public VolatileFieldAccess(int fieldId, int operation) {
        super();
        this.operation = operation;
        this.fieldId = fieldId;
    }

    private class CreateOrderVisitor implements SyncActionVisitor {
        private final TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements;
        private final Position myPosition;
        private final Position otherPosition;

        public CreateOrderVisitor(TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements,
                                  Position myPosition, Position otherPosition) {
            this.alternatingOrderElements = alternatingOrderElements;
            this.myPosition = myPosition;
            this.otherPosition = otherPosition;
        }

        @Override
        public void visit(VolatileFieldAccess volatileFieldAccess) {
            if (fieldId == volatileFieldAccess.fieldId && (operation | volatileFieldAccess.operation) >= MIN_OPERATION) {
                alternatingOrderElements.add(new TLinkableWrapper<AlternatingOrderElement>(
                        new AlternatingOrderElement(new LeftBeforeRight(myPosition, otherPosition))));
            }
        }
    }

    @Override
    public void createOrder(SyncAction other,
                            TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                            TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements,
                            Position myPosition,
                            Position otherPosition) {
        other.accept(new CreateOrderVisitor(alternatingOrderElements, myPosition, otherPosition));
    }

    @Override
    public void accept(SyncActionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "VolatileFieldAccess{" +
                "fieldId=" + fieldId +
                ", operation=" + operation +
                '}';
    }
}
