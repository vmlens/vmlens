package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;


/**
 * Let you iterate over all calculated runs which can be created out of the alternating order. The iterator return null
 * when an order leads to an impossible calculated run.
 */
public class AlternatingOrderContainer  {

    private ThreadIndexToElementList<Position> actualRun;
    private LeftBeforeRight[] fixedOrderArray;
    private OrderList orderList;
    private final InterleaveLoopContext interleaveLoopContext;

    public AlternatingOrderContainer(ThreadIndexToElementList<Position> actualRun,
                                     LeftBeforeRight[] fixedOrderArray,
                                     OrderList orderList,
                                     InterleaveLoopContext interleaveLoopContext) {
        this.actualRun = actualRun;
        this.fixedOrderArray = fixedOrderArray;
        this.orderList = orderList;
        this.interleaveLoopContext = interleaveLoopContext;
    }

    public ThreadIndexToElementList<Position> actualRun() {
        return actualRun;
    }



    void setFieldsToNull() {
        actualRun = null;
        fixedOrderArray = null;
        orderList = null;
    }

    /**
     * Iterator can return null, will be filtered by InterleaveLoopIteratorStateAlternatingOrderContainer
     */
    public AlternatingOrderContainerIterator iterator() {
        return new AlternatingOrderContainerIterator(this);
    }

    InterleaveLoopContext interleaveLoopContext() {
        return interleaveLoopContext;
    }

    OrderList orderList() {
        return orderList;
    }

    LeftBeforeRight[] fixedOrderArray() {
        return fixedOrderArray;
    }

    void resetOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

}
