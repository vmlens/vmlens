package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadStartedByPoolContext;

public class RunAdapter {

    private final RunForCallback run;

    public RunAdapter(RunForCallback run) {
        this.run = run;
    }

    public void after(AfterContext afterContext) {
        afterContext.runtimeEvent().setThreadIndex(afterContext.threadLocalDataWhenInTest().threadIndex());
        run.after(afterContext);
    }

    public void newTestTaskStarted(ThreadWrapper newWrapper) {
        run.threadStarted(newWrapper);
    }

    public void threadStartedByPool(ThreadStartedByPoolContext context) {
        run.threadStartedByPool(context);
    }

    public void threadJoinedByPool(JoinAction threadJoinedAction) {
        run.threadJoinedByPool(threadJoinedAction);
    }
}
