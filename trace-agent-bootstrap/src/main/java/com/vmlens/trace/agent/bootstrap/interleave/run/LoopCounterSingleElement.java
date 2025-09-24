package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.iterator.TIntIterator;

import static com.vmlens.trace.agent.bootstrap.exception.Message.NON_VOLATILE_LOOP_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.exception.Message.SYNC_ACTION_LOOP_MESSAGE;

public class LoopCounterSingleElement implements LoopCounter {

    private int forThreadIndex ;
    private int pluginEventOnly;
    private int interleaveAction;

    @Override
    public InterleaveRunState onPluginEvent(int threadIndex,
                                            ThreadIndexAndThreadStateMap context,
                                            SendEvent sendEvent,
                                            InterleaveRunState previous) {
        if(forThreadIndex != threadIndex) {
            forThreadIndex = threadIndex;
            pluginEventOnly = 0;
            interleaveAction = 0;
            return previous;
        }
        pluginEventOnly++;

        if(pluginEventOnly < 5000) {
            return previous;
        }

        sendEvent.sendMessage(NON_VOLATILE_LOOP_MESSAGE);
        return createInterleaveRunState(threadIndex,context);
    }

    @Override
    public InterleaveRunState onInterleaveActionFactory(int threadIndex,
                                                        ThreadIndexAndThreadStateMap context,
                                                        SendEvent sendEvent,
                                                        InterleaveRunState previous) {
        if(forThreadIndex != threadIndex) {
            forThreadIndex = threadIndex;
            pluginEventOnly = 0;
            interleaveAction = 0;
            return previous;
        }
        interleaveAction++;
        pluginEventOnly = 0;

        if(interleaveAction < 500) {
            return previous;
        }

        sendEvent.sendMessage(SYNC_ACTION_LOOP_MESSAGE);
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
