package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.warning.InfoMessageEvent;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

import java.util.Arrays;

import static com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated.calculateActiveByPositionInRun;
import static com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated.calculateActiveThreadIndex;

public class InterleaveRunWithCalculated implements InterleaveRun {

    private final ActualRun actualRun = new ActualRun();
    private final Position[] calculatedRunElementArray;
    private final ThreadIndexToElementList<Position> calculatedRunPerThread;

    public InterleaveRunWithCalculated(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
        this.calculatedRunPerThread = new ThreadIndexToElementList<>();
        for(Position position : calculatedRunElementArray) {
            calculatedRunPerThread.add(position);
        }
    }

    @Override
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        InterleaveInfo info = actualRun.after(interleaveAction);
        if( info != null) {
            calculatedRunPerThread.popIfNotEmpty(interleaveAction.threadIndex());
        }
        return info;

    }

    @Override
    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return actualRun.run();
    }

    public boolean isActive(int threadIndex, TIntLinkedList activeThreadIndices) {
        if (actualRun.positionInRun() >= calculatedRunElementArray.length) {
            return calculateActiveByPositionInRun(actualRun.positionInRun(),threadIndex,activeThreadIndices);
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
            return true;
        }
        return calculatedRunElementArray[actualRun.positionInRun()].threadIndex() == threadIndex;
    }

    public Integer activeThreadIndex(TIntLinkedList activeThreadIndices) {
        if (actualRun.positionInRun() >= calculatedRunElementArray.length) {
            return calculateActiveThreadIndex(activeThreadIndices);
        }
        return calculatedRunElementArray[actualRun.positionInRun()].threadIndex();
    }

    @Override
    public ActualRun actualRun() {
        return actualRun;
    }

    @Override
    public boolean withCalculated() {
        return true;
    }

    @Override
    public void logCalculatedRun(SendEvent sendEvent) {
        String[] message = new String[] {Arrays.toString(calculatedRunElementArray) , ""  +actualRun.positionInRun() };
        sendEvent.sendSerializable(new InfoMessageEvent(message));
    }
}
