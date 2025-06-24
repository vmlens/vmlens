package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.DeadlockOperation;
import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.BlockBlockTuple;
import gnu.trove.map.hash.THashMap;

/**
 *
 * this is the context to process DependentBlockOrOperation
 * contains the deadlock information
 *
 */
public class BuildAlternatingOrderContext {

    private final ThreadIndexToMaxPosition threadIndexToMaxPosition;
    private final THashMap<Position, DeadlockOperation> deadlockMap;

    public BuildAlternatingOrderContext(ThreadIndexToMaxPosition threadIndexToMaxPosition,
                                        THashMap<Position, DeadlockOperation> deadlockMap) {
        this.threadIndexToMaxPosition = threadIndexToMaxPosition;
        this.deadlockMap = deadlockMap;
    }

    public int getLastPositionForThreadIndex(int threadIndex) {
        return threadIndexToMaxPosition.getPositionAtThreadIndex(threadIndex);
    }

    public boolean isInDeadlock(BlockBlockTuple tuple) {
        DeadlockOperation deadlock = deadlockMap.get(tuple.first().start().position());
        if(deadlock == null) {
            return false;
        }
        return deadlock.isInDeadlock(tuple);
    }


}
