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

    public boolean sendAsInterleaveEvent(Class fieldAccessEventStaticGenClass) {
        // ToDo implement correctly
        return true;
    }

    public RunnableOrThreadWrapper createdThread() {
        return createdThread;
    }

    public void setCreatedThread(RunnableOrThreadWrapper createdThread) {
        this.createdThread = createdThread;
    }

    public boolean showNonVolatileMemoryAccess() {
        // ToDo implement correctly
        return true;
    }

    public int loopId() {
        // ToDo implement correctly
        return 0;
    }

    public int runId() {
        // ToDo implement correctly
        return 0;
    }

    public int runPosition() {
        // ToDo implement correctly
        return 0;
    }
}
