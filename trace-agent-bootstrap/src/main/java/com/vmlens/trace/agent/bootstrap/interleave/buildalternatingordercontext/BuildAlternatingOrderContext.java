package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;

/**
 *
 * this is the context to process DependentBlockOrOperation
 * contains the deadlock information
 *
 */

public class BuildAlternatingOrderContext {

    private final ThreadIndexToMaxPosition threadIndexToMaxPosition;

    public BuildAlternatingOrderContext(ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        this.threadIndexToMaxPosition = threadIndexToMaxPosition;
    }

    public int getLastPositionForThreadIndex(int threadIndex) {
        return threadIndexToMaxPosition.getPositionAtThreadIndex(threadIndex);
    }

}
