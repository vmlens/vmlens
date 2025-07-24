package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NextStateBuilder;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import gnu.trove.set.hash.TIntHashSet;

public class NextStateBuilderImpl implements NextStateBuilder {

    private RunState nextState;
    private final ProcessLockExitOrWaitTemplate parent;

    public NextStateBuilderImpl(ProcessLockExitOrWaitTemplate parent) {
        this.parent = parent;
    }

    public RunState build() {
        return nextState;
    }


    @Override
    public void addWaitingThreadIndex(int threadIndex) {
        TIntHashSet newThreadIndices = parent.notYetWaitingThreadIndices();
        newThreadIndices.add(threadIndex);
        setStateActiveOrWait(newThreadIndices);
    }

    @Override
    public void addExitEvent() {
        setStateActiveOrWait(parent.notYetWaitingThreadIndices());
    }


    @Override
    public int addThreadStarted(ThreadWrapper startedThread) {
        int threadIndexForNewTestTask = parent.runStateContext().getThreadIndexForNewTestThread();
        nextState = new RunStateNewThreadStarted(parent.runStateContext(),
                startedThread,
                threadIndexForNewTestTask,
                parent);
        return threadIndexForNewTestTask;
    }

    private void setStateActiveOrWait(TIntHashSet notYetWaitingThreadIndices) {
        TIntHashSet newThreadIndices = notYetWaitingThreadIndices;
        newThreadIndices = parent.runStateContext().removeNotActive(newThreadIndices);
        if(! newThreadIndices.isEmpty()) {
            nextState = parent.runStateWaiting(newThreadIndices);
        } else {
            nextState = parent.runStateActive();
        }
    }


}
