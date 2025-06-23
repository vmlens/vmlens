package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

/**
 *
 * this is the context to process DependentBlockOrOperation
 * contains the deadlock information
 *
 */

public interface BuildAlternatingOrderContext {

    int getLastPositionForThreadIndex(int threadIndex);

}
