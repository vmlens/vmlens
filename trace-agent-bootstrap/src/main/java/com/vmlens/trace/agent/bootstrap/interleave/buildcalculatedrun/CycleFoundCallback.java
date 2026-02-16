package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;

public interface CycleFoundCallback {

    void found(OrderCycle[] array);

}
