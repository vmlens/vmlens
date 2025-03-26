package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ExecuteSynchronizedMethodExit implements ExecuteAfterMethodCall {

    private final RunAfter<MonitorEnterEvent> monitorExit;
    private final RunAfter<MethodEnterEvent> methodExit;


    public ExecuteSynchronizedMethodExit(RunAfter<MonitorEnterEvent> monitorExit,
                                         RunAfter<MethodEnterEvent> methodExit) {
        this.monitorExit = monitorExit;
        this.methodExit = methodExit;
    }

    @Override
    public void execute(int inMethodId, int position, ThreadLocalWhenInTest threadLocalDataWhenInTest, QueueIn queueIn) {
        monitorExit.execute(threadLocalDataWhenInTest,queueIn);
        methodExit.execute(threadLocalDataWhenInTest,queueIn);

    }
}
