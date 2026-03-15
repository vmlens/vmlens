package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.filteractions.WithoutFilterActions;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunAfterLockExitWaitOrThreadStart;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.instant.RunBeforeLockExitOrWait;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetInMethodIdPositionObjectHashCode;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.*;

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

    public static void beforeMonitorExit(EventContext context,int position) {
        context.inTestActionProcessor().process(createBeforeMonitorExit(context,position));
    }

    public static void afterMonitorExit(EventContext context) {
        context.inTestActionProcessor().process(createAfterMonitorExit());
    }

    public static RunBeforeLockExitOrWait<MonitorExitEvent> createBeforeMonitorExit(EventContext context,int position) {
        return new RunBeforeLockExitOrWait<>(new MonitorExitEvent(context.methodId(), position),
                new SetInMethodIdPositionObjectHashCode<>(context.object()), new WithoutFilterActions());
    }

    public static RunAfterLockExitWaitOrThreadStart createAfterMonitorExit() {
        return new RunAfterLockExitWaitOrThreadStart();
    }

    public static RunAfter<MethodExitEvent> createMethodExit(EventContext context) {
        return new RunAfter<>(new MethodExitEvent(),
                new SetFieldsNoOp<>());
    }

}
