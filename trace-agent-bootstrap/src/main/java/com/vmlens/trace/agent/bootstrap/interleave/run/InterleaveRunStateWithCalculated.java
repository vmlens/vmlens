package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.list.linked.TIntLinkedList;

import java.util.Arrays;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;

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
    public StateAndThreadIndex activeThreadIndex(TIntLinkedList activeThreadIndices) {
        if (arrayIndex >= calculatedRunElementArray.length) {
            int position = activeThreadIndices.size() - 1;
            int index = activeThreadIndices.get(position);
            return new StateAndThreadIndex(new InterleaveRunStateWithoutCalculated(index), index);
        }
        return new StateAndThreadIndex(this,calculatedRunElementArray[arrayIndex].threadIndex());
    }

    @Override
    public StateAndIsActive isActive(int threadIndex, TIntLinkedList activeThreadIndices) {
        if (arrayIndex >= calculatedRunElementArray.length) {
            int position = activeThreadIndices.size() - 1;
            int index = activeThreadIndices.get(position);
            return new StateAndIsActive(new InterleaveRunStateWithoutCalculated(index), index == threadIndex);
        }
        /*
          The last after call of a thread needs to be enabled
          for example:
              Thread0.run
              volatile read
          has only one interleave action in the array for the volatile read
          but the after for the volatile field should not block
         */
        if(calculatedRunPerThread.isEmptyAtIndex(threadIndex)) {
            return  new StateAndIsActive(this,true);
        }
        return new StateAndIsActive(this,calculatedRunElementArray[arrayIndex].threadIndex() == threadIndex);
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
            arrayIndex++;
            calculatedRunPerThread.popIfNotEmpty(index);
            return loopCounter.onInterleaveActionFactory(index,context.context(),afterCallback,this);
        }
       throw new RuntimeException("should not be called");
    }

    @Override
    public InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                                   SendEvent sendEvent,
                                                   int activeThreadIndex) {
        sendEvent.sendMessage(TEST_BLOCKED_MESSAGE);
        logCalculatedRun(sendEvent);
        runContext.logStackTrace(activeThreadIndex,sendEvent);
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    @Override
    public InterleaveRunState onBlockedWithoutLogging(int activeThreadIndex) {
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    private void logCalculatedRun(SendEvent sendEvent) {
        String[] message = new String[] {Arrays.toString(calculatedRunElementArray) , "" + arrayIndex};
        sendEvent.sendSerializable(new InfoMessageEvent(message));
    }
}
