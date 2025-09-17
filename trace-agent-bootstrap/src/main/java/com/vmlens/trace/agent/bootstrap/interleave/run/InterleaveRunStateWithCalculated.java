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

    public InterleaveRunStateWithCalculated(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
        this.calculatedRunPerThread = new ThreadIndexToElementList<>();
        for(Position position : calculatedRunElementArray) {
            calculatedRunPerThread.add(position);
        }
    }

    @Override
    public StateAndThreadIndex activeThreadIndex(TIntLinkedList activeThreadIndices, int positionInRun) {
        if (positionInRun >= calculatedRunElementArray.length) {
            int position = activeThreadIndices.size() - 1;
            int index = activeThreadIndices.get(position);
            return new StateAndThreadIndex(new InterleaveRunStateWithoutCalculated(index), index);
        }
        return new StateAndThreadIndex(this,calculatedRunElementArray[positionInRun].threadIndex());
    }

    @Override
    public StateAndIsActive isActive(int threadIndex, int positionInRun, TIntLinkedList activeThreadIndices) {
        if (positionInRun >= calculatedRunElementArray.length) {
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

        return new StateAndIsActive(this,calculatedRunElementArray[positionInRun].threadIndex() == threadIndex);
    }

    @Override
    public InterleaveRunState after(ProcessEventContext context,
                                    InterleaveRun interleaveRun,
                                    EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent) {
        PluginEventOnly pluginEvent = runtimeEvent.asPluginEventOnly();
        if(pluginEvent != null) {
            interleaveRun.process(context,pluginEvent);
        }
        InterleaveActionFactory interleaveActionFactory = runtimeEvent.asInterleaveActionFactory();
        if(interleaveActionFactory != null) {
            int index = interleaveRun.process(context,interleaveActionFactory);
            calculatedRunPerThread.popIfNotEmpty(index);
        }
        return this;
    }

    @Override
    public InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                                   SendEvent sendEvent,
                                                   int activeThreadIndex,
                                                   int positionInRun) {
        sendEvent.sendMessage(TEST_BLOCKED_MESSAGE);
        logCalculatedRun(sendEvent,positionInRun);
        runContext.logStackTrace(activeThreadIndex,sendEvent);
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    @Override
    public InterleaveRunState onBlockedWithoutLogging(int activeThreadIndex) {
        return new InterleaveRunStateWithoutCalculated(activeThreadIndex);
    }

    private void logCalculatedRun(SendEvent sendEvent, int positionInRun) {
        String[] message = new String[] {Arrays.toString(calculatedRunElementArray) , "" + positionInRun};
        sendEvent.sendSerializable(new InfoMessageEvent(message));
    }
}
