package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public interface BuildAlternatingOrderContext {

    void either(OrderAlternative alternativeA, OrderAlternative alternativeB);
    void newChoice();
    int getLastPositionForThreadIndex(int threadIndex);

}
