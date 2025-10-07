package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.linked.TIntLinkedList;

public class InterleaveRunStateWithoutCalculated implements InterleaveRunState  {

    private final int currentThreadIndex;
    private final LoopCounter loopCounter = new LoopCounter();

    public InterleaveRunStateWithoutCalculated(int activeThreadIndex) {
        this.currentThreadIndex = activeThreadIndex;
    }

    @Override
    public StateAndThreadIndex activeThreadIndex(TIntLinkedList activeThreadIndices) {
        TIntIterator iter = activeThreadIndices.iterator();
        while(iter.hasNext()) {
            int index = iter.next();
            if(index == currentThreadIndex) {
                return new StateAndThreadIndex(this,currentThreadIndex);
            }
        }
        int position = activeThreadIndices.size() - 1;
        int index = activeThreadIndices.get(position);
        return new StateAndThreadIndex(new InterleaveRunStateWithoutCalculated(index), index);
    }

    @Override
    public StateAndIsActive isActive(int threadIndex,
                                     TIntLinkedList activeThreadIndices) {
        TIntIterator iter = activeThreadIndices.iterator();
        while(iter.hasNext()) {
            int index = iter.next();
            if(index == currentThreadIndex) {
                return new StateAndIsActive(this,currentThreadIndex == threadIndex);
            }
        }
        int position = activeThreadIndices.size() - 1;
        int index = activeThreadIndices.get(position);
        return new StateAndIsActive(new InterleaveRunStateWithoutCalculated(index), index == threadIndex);
    }

    @Override
    public InterleaveRunState after(ProcessEventContext context,
                                    AfterCallback afterCallback,
                                    EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent) {
        PluginEventOnly pluginEvent = runtimeEvent.asPluginEventOnly();
        if(pluginEvent != null) {
            afterCallback.process(context,pluginEvent);
            return loopCounter.onPluginEvent(pluginEvent.threadIndex(),context.context(),afterCallback,this);

        }
        InterleaveActionFactory interleaveActionFactory = runtimeEvent.asInterleaveActionFactory();
        if(interleaveActionFactory != null) {
            int index = afterCallback.process(context,interleaveActionFactory);
            return loopCounter.onInterleaveActionFactory(index,context.context(),afterCallback,this);
        }
        throw new RuntimeException("should not be called");
    }

    @Override
    public InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext, SendEvent sendEvent, int activeThreadIndex) {
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    @Override
    public InterleaveRunState onBlockedWithoutLogging(int activeThreadIndex) {
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

}
