package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;

public class EventUtil {

    public static void methodEnterEvent(EventContext context) {
        context.inTestActionProcessor().process(new RunAfter<>(new MethodEnterEvent(
                context.methodId()),
                new SetFieldsNoOp<>()));
    }

    public static void methodExitEvent(EventContext context) {
        context.inTestActionProcessor().process(createMethodExit(context));
    }

    public static void monitorEnter(EventContext context, int position) {
        context.inTestActionProcessor().process(new RunAfter<>(new MonitorEnterEvent(
                context.methodId(), position),
                new SetObjectHashCode<>(context.object())));
    }

    public static void monitorExit(EventContext context,int position) {
        context.inTestActionProcessor().process(createMonitorExit(context,position));
    }

    public static RunAfter<MonitorExitEvent> createMonitorExit(EventContext context,int position) {
        return new RunAfter<>(new MonitorExitEvent(
                context.methodId(), position),
                new SetObjectHashCode<>(context.object()));
    }

    public static RunAfter<MethodExitEvent> createMethodExit(EventContext context) {
        return new RunAfter<>(new MethodExitEvent(),
                new SetFieldsNoOp<>());
    }

}
