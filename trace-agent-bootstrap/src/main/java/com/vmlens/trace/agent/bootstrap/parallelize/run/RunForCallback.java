package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;

public interface RunForCallback {

    void after(AfterContext afterContext);

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
