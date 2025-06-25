package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;
import gnu.trove.set.hash.TIntHashSet;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.ProcessAfter.process;

public abstract class ProcessLockExitOrWaitTemplate {

    public RunState beforeLockExitOrWait(LockExitOrWaitEvent lockExitOrWaitEvent,
                                  ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                  SendEvent sendEvent) {
        AfterContextForStateMachine afterContext = new
                AfterContextForStateMachine(threadLocalDataWhenInTest,lockExitOrWaitEvent);
        process(afterContext, sendEvent, runStateContext(), startedThreadIndex());

        TIntHashSet newThreadIndices = notYetWaitingThreadIndices();
        Integer waiting = lockExitOrWaitEvent.waitingThreadIndex();
        if(waiting != null) {
            newThreadIndices.add(waiting);
        }
        newThreadIndices = runStateContext().removeNotActive(newThreadIndices);
        if(! newThreadIndices.isEmpty()) {
            return runStateWaiting(newThreadIndices);
        }
        return runStateActive();
    }

    protected abstract RunStateContext runStateContext();
    protected abstract int startedThreadIndex();
    protected abstract TIntHashSet notYetWaitingThreadIndices();
    protected abstract RunState runStateWaiting(TIntHashSet newThreadIndices);
    protected abstract RunState runStateActive();

}
