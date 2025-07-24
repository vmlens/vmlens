package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.JoinAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunForCallback;
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

    public void beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent,
                                                ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                                QueueIn queueIn) {
        lockExitOrWaitEvent.setThreadIndex(threadLocalDataWhenInTest.threadIndex());
        run.beforeLockExitWaitOrThreadStart(lockExitOrWaitEvent,threadLocalDataWhenInTest,queueIn);
    }

    public void afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                               QueueIn queueIn) {
        run.afterLockExitWaitOrThreadStart(threadLocalDataWhenInTest,queueIn);
    }

    public void threadStartedByPool(ThreadStartedByPoolContext context) {
        run.threadStartedByPool(context);
    }

    public void threadJoinedByPool(JoinAction threadJoinedAction) {
        run.threadJoinedByPool(threadJoinedAction);
    }
}
