package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public class ParallelizedThreadLocal {
    private final Run run;

    public ParallelizedThreadLocal(Run run) {
        this.run = run;
    }

    public void after(ParallelizeAction action) {
        run.after(action);
    }

}
