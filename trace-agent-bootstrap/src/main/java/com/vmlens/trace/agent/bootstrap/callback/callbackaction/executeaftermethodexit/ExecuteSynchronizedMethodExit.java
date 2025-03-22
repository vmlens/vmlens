package com.vmlens.trace.agent.bootstrap.callback.callbackaction.executeaftermethodexit;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ExecuteSynchronizedMethodExit implements ExecuteAfterMethodCall {

    private final RunAfter<MonitorExitEvent> monitorExit;
    private final RunAfter<MethodExitEvent> methodExit;


    public ExecuteSynchronizedMethodExit(RunAfter<MonitorExitEvent> monitorExit,
                                         RunAfter<MethodExitEvent> methodExit) {
        this.monitorExit = monitorExit;
        this.methodExit = methodExit;
    }

    @Override
    public TLinkedList<TLinkableWrapper<SerializableEvent>> execute(int inMethodId,
                                                                    int position,
                                                                    ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        TLinkedList<TLinkableWrapper<SerializableEvent>> result =
                monitorExit.execute(threadLocalDataWhenInTest);
        result.addAll(methodExit.execute(threadLocalDataWhenInTest));
        return result;
    }
}
