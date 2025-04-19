package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;

public class RunAdapter {

    private final RunForCallback run;

    public RunAdapter(RunForCallback run) {
        this.run = run;
    }

    public void after(AfterContext afterContext) {
        afterContext.runtimeEvent().setThreadIndex(afterContext.threadLocalDataWhenInTest().threadIndex());
        run.after(afterContext);
    }

    public void newTestTaskStarted(RunnableOrThreadWrapper newWrapper) {
        run.newTestTaskStarted(newWrapper);
    }

}
