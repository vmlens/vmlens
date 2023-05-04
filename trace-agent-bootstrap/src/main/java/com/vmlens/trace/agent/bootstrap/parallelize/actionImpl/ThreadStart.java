package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;


import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ThreadStart implements ParallelizeAction  {

    private final RunnableOrThreadWrapper startedThread;

    public ThreadStart(RunnableOrThreadWrapper startedThread) {
        this.startedThread = startedThread;
    }

    @Override
    public RunState nextState(ActionContext context, TestThreadState testThreadState) {
        return context.threadStarted(startedThread, testThreadState);
    }

    @Override
    public void addInterleaveAction(ActionContext context, TestThreadState testThreadState) {
        // Nothing to do after gets called inside RunStateNewThreadStarted
    }


}
