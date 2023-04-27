package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public class ParallelizedThreadLocal {
    private final Run run;
    private final int threadIndex;

    public ParallelizedThreadLocal(Run run, int threadIndex) {
        this.run = run;
        this.threadIndex = threadIndex;
    }
    void after(ParallelizeAction action, TestThreadState testThreadState) {
         run.after(action,testThreadState);
    }
    // public for test
    public int threadIndex() {
        return threadIndex;
    }
    // visible for test
    public Run getRun() {
        return run;
    }
}
