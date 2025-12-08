package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;

public abstract class AbstractOrderArrayListTestBuilder {

    private final OrderArrayList orderArrayList = new OrderArrayList(new LeftBeforeRight[0]);

    public OrderArrayList build() {
        addOrder();
        return orderArrayList;
    }

    public void lbr(int leftThreadIndex, int leftPositionInThread,
                           int rightThreadIndex, int rightPositionInThread ) {
        orderArrayList.add(LeftBeforeRight.lbr( leftThreadIndex,  leftPositionInThread,
                rightThreadIndex,  rightPositionInThread));
    }

    protected abstract void addOrder();

}
