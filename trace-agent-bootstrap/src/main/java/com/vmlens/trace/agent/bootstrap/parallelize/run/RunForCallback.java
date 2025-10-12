package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;

public interface RunForCallback {

    void after(AfterContext afterContext);

    /**
     * The last action in a thread must not wait
     * We do not need a runtime event since it does not interest the
     * data race detection
     *
     */
    void afterLastThreadAction(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                               QueueIn queueIn,
                               RuntimeEvent runtimeEvent);

    void newTask(NewTaskContext newTaskContext);

    void threadStartedByPool(ThreadStartedByPoolContext context);

    void threadJoinedByPool(JoinAction threadJoinedAction);

    void beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent,
                                         ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                         QueueIn queueIn);

    void afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                        QueueIn queueIn);

    int runId();

    int loopId();


}
