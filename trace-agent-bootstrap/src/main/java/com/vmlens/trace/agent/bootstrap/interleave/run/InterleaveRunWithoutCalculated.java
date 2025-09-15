package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

public class InterleaveRunWithoutCalculated implements InterleaveRun {

    private final ActualRun actualRun;

    public InterleaveRunWithoutCalculated(ActualRun actualRun) {
        this.actualRun = actualRun;
    }

    @Override
    public InterleaveInfo after(InterleaveAction interleaveAction) {
        return actualRun.after(interleaveAction);
    }

    @Override
    public TLinkedList<TLinkableWrapper<InterleaveAction>> run() {
        return actualRun.run();
    }

    @Override
    public boolean isActive(int threadIndex, TIntLinkedList activeThreadIndices) {
        return calculateActiveByPositionInRun(actualRun.positionInRun(),threadIndex,activeThreadIndices);
    }

    @Override
    public Integer activeThreadIndex(TIntLinkedList activeThreadIndices) {
        return  calculateActiveThreadIndex(activeThreadIndices);
    }

    @Override
    public ActualRun actualRun() {
        return actualRun;
    }

    public static int calculateActiveThreadIndex(TIntLinkedList activeThreadIndices) {
        int position =  activeThreadIndices.size() - 1;
        return activeThreadIndices.get(position);
    }

    /**
     * To avoid class loading locks, see for example com.vmlens.test.maven.plugin.json.TestGson
     * we are not going round-robin but rather first the latest started thread
     *
     */
    public static boolean calculateActiveByPositionInRun(int positionInRun,
                                                         int threadIndex,
                                                         TIntLinkedList activeThreadIndices) {


        return  calculateActiveThreadIndex(activeThreadIndices) == threadIndex;
    }

    @Override
    public boolean withCalculated() {
        return false;
    }

    @Override
    public void logCalculatedRun(SendEvent sendEvent) {

    }
}
