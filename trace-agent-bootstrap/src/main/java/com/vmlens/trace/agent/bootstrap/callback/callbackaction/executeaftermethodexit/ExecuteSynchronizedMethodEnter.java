package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;

public class ExecuteSynchronizedMethodEnter implements ExecuteAfterMethodCall {

    private final RunAfter<MethodEnterEvent> methodEnter;
    private final RunAfter<MonitorEnterEvent> monitorEnter;



    public ExecuteSynchronizedMethodEnter(RunAfter<MethodEnterEvent> methodEnter,
                                          RunAfter<MonitorEnterEvent> monitorEnter) {
        this.methodEnter = methodEnter;
        this.monitorEnter = monitorEnter;
    }

    @Override
    public void execute(int inMethodId, int position, ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        methodEnter.execute(threadLocalDataWhenInTest,queueIn);
        monitorEnter.execute(threadLocalDataWhenInTest,queueIn);


    }
}
