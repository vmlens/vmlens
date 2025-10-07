package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.iterator.TIntIterator;

public class LoopCounter {
    private int forThreadIndex ;
    private int pluginEventOnly;
    private int interleaveAction;

    public InterleaveRunState onPluginEvent(int threadIndex,
                                            ThreadIndexAndThreadStateMap context,
                                            AfterCallback afterCallback,
                                            InterleaveRunState previous) {
        if(forThreadIndex != threadIndex) {
            forThreadIndex = threadIndex;
            pluginEventOnly = 0;
            interleaveAction = 0;
            return previous;
        }
        pluginEventOnly++;

        if(pluginEventOnly < afterCallback.unsynchronizedOperationsLoopThreshold()) {
            return previous;
        }

        afterCallback.onNonVolatileLoop();
        return createInterleaveRunState(threadIndex,context);
    }


    public InterleaveRunState onInterleaveActionFactory(int threadIndex,
                                                        ThreadIndexAndThreadStateMap context,
                                                        AfterCallback afterCallback,
                                                        InterleaveRunState previous) {
        if(forThreadIndex != threadIndex) {
            forThreadIndex = threadIndex;
            pluginEventOnly = 0;
            interleaveAction = 0;
            return previous;
        }
        interleaveAction++;
        pluginEventOnly = 0;

        if(interleaveAction < afterCallback.synchronizationActionsLoopThreshold()) {
            return previous;
        }

        afterCallback.onSynchronizedActionLoop();
        return createInterleaveRunState(threadIndex,context);
    }

    static InterleaveRunState createInterleaveRunState(int threadIndex,
                                                       ThreadIndexAndThreadStateMap context) {
        TIntIterator iter = context.getActiveThreadIndices().iterator();
        while(iter.hasNext()) {
            int index = iter.next();
            if(index != threadIndex) {
                return new InterleaveRunStateWithoutCalculated(index);
            }

        }
        return new InterleaveRunStateWithoutCalculated(threadIndex);
    }
}
