package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ParallelizedThreadLocal {
    private final Run run;
    private final int threadIndex;
    private RunnableOrThreadWrapper createdThread;

    public ParallelizedThreadLocal(Run run, int threadIndex) {
        this.run = run;
        this.threadIndex = threadIndex;
    }
    void after(ParallelizeAction action, TestThreadState testThreadState) {
        run.after(action, testThreadState);
    }

    // public for test
    public int threadIndex() {
        return threadIndex;
    }

    // visible for test
    public Run getRun() {
        return run;
    }


    public RunnableOrThreadWrapper createdThread() {
        return createdThread;
    }

    public void setCreatedThread(RunnableOrThreadWrapper createdThread) {
        this.createdThread = createdThread;
    }


}
