package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.linked.TIntLinkedList;

import static com.vmlens.trace.agent.bootstrap.TraceFlags.TRACE_BLOCKED;

public class InterleaveRunStateWithoutCalculated implements InterleaveRunState  {

    private final int currentThreadIndex;
    private final LoopCounter loopCounter = new LoopCounter();

    public InterleaveRunStateWithoutCalculated(int activeThreadIndex) {
        this.currentThreadIndex = activeThreadIndex;
    }

    static InterleaveRunStateWithoutCalculated createNewStateAfterBlocked(ThreadIndexAndThreadStateMap runContext,
                                                                          SendEvent sendEvent,
                                                                          int blockedThreadIndex) {
        if(TRACE_BLOCKED) {
            runContext.logStackTrace(sendEvent,blockedThreadIndex);
        }
        int activeThreadIndex = 0;
        TIntIterator iterator = runContext.getActiveThreadIndices().iterator();
        while(iterator.hasNext()) {
            int current = iterator.next();
            if(blockedThreadIndex != current) {
                activeThreadIndex = current;
                break;
            }
        }
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    @Override
    public int activeThreadIndex(TIntLinkedList activeThreadIndices) {
      return currentThreadIndex;
    }

    @Override
    public StateAndIsActive isActive(int threadIndex) {
        return new StateAndIsActive(this, currentThreadIndex == threadIndex);
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
            return loopCounter.onInterleaveActionFactory(index,context.context(),afterCallback);
        }
        throw new RuntimeException("should not be called");
    }

    @Override
    public InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                                   SendEvent sendEvent,
                                                   int blockedThreadIndex) {
        return createNewStateAfterBlocked( runContext, sendEvent, blockedThreadIndex);
    }


}
