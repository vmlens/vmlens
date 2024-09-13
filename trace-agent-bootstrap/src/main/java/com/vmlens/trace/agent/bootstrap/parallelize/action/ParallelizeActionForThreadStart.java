package com.vmlens.trace.agent.bootstrap.parallelize.action;


import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateAndRuntimeEvent;

public class ParallelizeActionForThreadStart implements ParallelizeAction {

    private final RunnableOrThreadWrapper startedThread;
    private final int threadIndex;

    public ParallelizeActionForThreadStart(RunnableOrThreadWrapper startedThread, int threadIndex) {
        this.startedThread = startedThread;
        this.threadIndex = threadIndex;
    }
    @Override
    public RunStateAndRuntimeEvent execute(ActionContext context) {
        ThreadStartEvent threadStartEvent = new ThreadStartEvent();
        threadStartEvent.setThreadIndex(threadIndex);
        int startedIndex = context.getThreadIndexForNewTestThread();
        threadStartEvent.setStartedThreadId(startedIndex);

        return new RunStateAndRuntimeEvent(context.threadStarted(startedThread, startedIndex), threadStartEvent);
    }
}
