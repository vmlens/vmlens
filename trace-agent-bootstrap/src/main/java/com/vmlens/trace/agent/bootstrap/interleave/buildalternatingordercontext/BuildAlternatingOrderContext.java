package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

public interface BuildAlternatingOrderContext {

    int getLastPositionForThreadIndex(int threadIndex);

}
