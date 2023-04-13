package com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class Either<LEFT extends  EitherPart<LEFT,RIGHT>,RIGHT extends EitherPart<LEFT,RIGHT>>
        implements OrderElementFactory<Either<LEFT,RIGHT> > {

    private final LEFT left;
    private final RIGHT right;

    public static <LEFT extends  EitherPart<LEFT,RIGHT>,RIGHT extends EitherPart<LEFT,RIGHT>>
    Either<LEFT,RIGHT> left(LEFT left) {
        return new  Either( left, null);
    }

    public static <LEFT extends  EitherPart<LEFT,RIGHT>,RIGHT extends EitherPart<LEFT,RIGHT>>
    Either<LEFT,RIGHT> right(RIGHT right) {
        return new  Either( null, right);
    }

    private Either(LEFT left, RIGHT right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void createOrder(Position myPosition,
                            OrderElementFactoryAndPosition<Either<LEFT, RIGHT>> other,
                            DeadlockContext deadlockContext,
                            TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                            TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements) {
        if(left != null ) {
            if(other.orderElementFactory.left != null) {
                left.createOrderLeft(myPosition,other.orderElementFactory.left,other.position,
                        deadlockContext,fixedOrderElements,alternatingOrderElements);
            } else {
                left.createOrderRight(myPosition,other.orderElementFactory.right,other.position,
                        deadlockContext,fixedOrderElements,alternatingOrderElements);
            }
        } else {
            if(other.orderElementFactory.left != null) {
                right.createOrderLeft(myPosition,other.orderElementFactory.left,other.position,
                        deadlockContext,fixedOrderElements,alternatingOrderElements);
            } else {
                right.createOrderRight(myPosition,other.orderElementFactory.right,other.position,
                        deadlockContext,fixedOrderElements,alternatingOrderElements);
            }
        }

    }
}
