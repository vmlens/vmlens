package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.list.linked.TIntLinkedList;

import static com.vmlens.trace.agent.bootstrap.TraceFlags.TRACE_BLOCKED;
import static com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunStateWithoutCalculated.createNewStateAfterBlocked;


public class InterleaveRunStateWithCalculated implements InterleaveRunState {

    private final Position[] calculatedRunElementArray;
    private final ThreadIndexToElementList<Position> calculatedRunPerThread;
    private int arrayIndex;
    private final LoopCounter loopCounter = new LoopCounter();

    public InterleaveRunStateWithCalculated(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
        this.calculatedRunPerThread = new ThreadIndexToElementList<>();
        for(Position position : calculatedRunElementArray) {
            calculatedRunPerThread.add(position);
        }
    }

    @Override
    public int activeThreadIndex(TIntLinkedList activeThreadIndices) {
        if (arrayIndex >= calculatedRunElementArray.length) {
            int position = activeThreadIndices.size() - 1;
            return activeThreadIndices.get(position);

        }
        return calculatedRunElementArray[arrayIndex].threadIndex();
    }

    @Override
    public StateAndIsActive isActive(int threadIndex) {

        /*
          The last after call of a thread needs to be enabled
          for example:
              Thread0.run
              volatile read
          has only one interleave action in the array for the volatile read
          but the after for the volatile field should not block
         */
        return new StateAndIsActive(this,calculatedRunElementArray[arrayIndex].threadIndex() == threadIndex);
    }

    @Override
    public InterleaveRunState after(ProcessEventContext context,
                                    AfterCallback afterCallback,
                                    EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent) {
        PluginEventOnly pluginEvent = runtimeEvent.asPluginEventOnly();
        if(pluginEvent != null) {
            afterCallback.process(context,pluginEvent);
            return loopCounter.onPluginEvent(pluginEvent.threadIndex(),context.context(),afterCallback);
        }
        InterleaveActionFactory interleaveActionFactory = runtimeEvent.asInterleaveActionFactory();
        if(interleaveActionFactory != null) {
            int index = afterCallback.process(context,interleaveActionFactory);
            arrayIndex++;
            calculatedRunPerThread.popIfNotEmpty(index);
            if (arrayIndex >= calculatedRunElementArray.length) {
                return new InterleaveRunStateWithoutCalculated(0);
            }
            return loopCounter.onInterleaveActionFactory(index,context.context(),afterCallback);
        }
       throw new RuntimeException("should not be called");
    }

    @Override
    public InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                                   SendEvent sendEvent,
                                                   int blockedThreadIndex) {
        if(TRACE_BLOCKED) {
            sendEvent.sendMessage(LoopWarningEvent.testBlocked());
        }
        return createNewStateAfterBlocked( runContext, sendEvent, blockedThreadIndex);
    }

}
