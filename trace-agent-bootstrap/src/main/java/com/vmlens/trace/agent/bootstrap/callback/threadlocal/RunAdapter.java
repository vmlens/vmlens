package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.specialevents.AutomaticTestMethodEvent;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
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

    public void afterLastThreadAction(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                      QueueIn queueIn,
                                      RuntimeEvent runtimeEvent) {
       run.afterLastThreadAction(threadLocalDataWhenInTest,queueIn,runtimeEvent);
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

    public void sendMessage(LoopWarningEvent message, QueueIn queueIn) {
        message.setLoopId(run.loopId());
        message.setRunId(run.runId());
        queueIn.offer(message);
    }



    public void automaticTestMethod(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                    QueueIn queueIn,
                                    int id,
                                    int automaticTestMethodId,
                                    int automaticTestType){
        AutomaticTestMethodEvent event = new AutomaticTestMethodEvent();
        event.setAutomaticTestId(id);
        event.setAutomaticTestMethodId(automaticTestMethodId);
        event.setAutomaticTestType(automaticTestType);
        event.setThreadIndex(threadLocalDataWhenInTest.threadIndex());
        event.setRunId(run.runId());
        event.setLoopId(run.loopId());
        queueIn.offer(event);
     }

}
