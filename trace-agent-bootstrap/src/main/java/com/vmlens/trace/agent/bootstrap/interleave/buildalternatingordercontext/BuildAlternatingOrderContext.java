package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;

/**
 *
 * this is the context to process DependentBlockOrOperation
 * contains the deadlock information
 *
 */

public interface BuildAlternatingOrderContext {

    int getLastPositionForThreadIndex(int threadIndex);

}
