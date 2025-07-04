package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public interface RunForCallback {

    void after(AfterContext afterContext);

    void newTask(NewTaskContext newTaskContext);

    void threadStarted(ThreadWrapper newWrapper);

    void threadStartedByPool(ThreadStartedByPoolContext context);

    void threadJoinedByPool(JoinAction threadJoinedAction);

    void waitCallOrBeforeLockExit(LockExitOrWaitEvent lockExitOrWaitEvent,
                                  ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                  QueueIn queueIn);

    void afterLockExitOrWait(ThreadLocalWhenInTest threadLocalDataWhenInTest,
                             QueueIn queueIn);

}
