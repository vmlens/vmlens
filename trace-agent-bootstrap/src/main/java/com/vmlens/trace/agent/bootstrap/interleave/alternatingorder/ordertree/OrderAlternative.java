package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;


import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public interface OrderAlternative   {

    boolean process(CreateOrderContext context);
    boolean createOrder(LeftBeforeRight leftBeforeRight);

}
