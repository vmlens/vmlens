package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;
import gnu.trove.set.hash.TIntHashSet;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.ProcessAfter.process;

public abstract class ProcessLockExitOrWaitTemplate implements RunState {

    public RunState beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent,
                                                    ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                                    SendEvent sendEvent) {
        // here the threadStartedThreadIndex gets set
        // so we need to call it before process
        NextStateBuilderImpl nextStateBuilderImpl = new NextStateBuilderImpl(this);
        lockExitOrWaitEvent.addToBuilder(nextStateBuilderImpl);

        AfterContextForStateMachine afterContext = new
                AfterContextForStateMachine(threadLocalDataWhenInTest,lockExitOrWaitEvent);
        process(afterContext, sendEvent, runStateContext());

        return nextStateBuilderImpl.build();
    }

    public RunState afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        TIntHashSet newThreadIndices = notYetWaitingThreadIndices();
        newThreadIndices.remove(threadLocalDataWhenInTest.threadIndex());
        if(! newThreadIndices.isEmpty()) {
            return runStateWaiting(newThreadIndices);
        }
        return runStateActive();
    }

    protected abstract RunStateContext runStateContext();
    protected abstract TIntHashSet notYetWaitingThreadIndices();
    protected abstract RunState runStateWaiting(TIntHashSet newThreadIndices);
    protected abstract RunState runStateActive();

}
