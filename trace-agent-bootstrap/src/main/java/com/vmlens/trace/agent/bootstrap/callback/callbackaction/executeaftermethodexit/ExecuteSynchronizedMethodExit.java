package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;

public class ExecuteSynchronizedMethodExit implements ExecuteAfterMethodCall {

    private final RunAfter<MonitorExitEvent> monitorExit;
    private final RunAfter<MethodExitEvent> methodExit;

    public ExecuteSynchronizedMethodExit(RunAfter<MonitorExitEvent> monitorExit,
            RunAfter<MethodExitEvent> methodExit
                                        ) {
        this.methodExit = methodExit;
        this.monitorExit = monitorExit;
    }

    @Override
    public void execute(int inMethodId, int position, ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        monitorExit.execute(threadLocalDataWhenInTest,queueIn);
        methodExit.execute(threadLocalDataWhenInTest,queueIn);
    }
}
