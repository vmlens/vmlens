package com.vmlens.trace.agent.bootstrap.parallelize.action;


import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class ParallelizeActionForThreadStart implements ParallelizeAction {

    private final RunnableOrThreadWrapper startedThread;

    public ParallelizeActionForThreadStart(RunnableOrThreadWrapper startedThread) {
        this.startedThread = startedThread;
    }

    @Override
    public RunState nextState(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return context.threadStarted(startedThread, threadLocalDataWhenInTest);
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        // Nothing to do. After gets called inside RunStateNewThreadStarted
    }


}
