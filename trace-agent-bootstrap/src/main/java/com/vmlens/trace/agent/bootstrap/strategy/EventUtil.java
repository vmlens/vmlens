package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.RunAfter;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetFieldsNoOp;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.setfields.SetObjectHashCode;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MethodExitEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.MonitorExitEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public class EventUtil {

    public static void methodEnterEvent(EventContext context) {
        context.threadLocalWhenInTestAdapter().process(new RunAfter<>(new MethodEnterEvent(
                context.methodId()),
                new SetFieldsNoOp<>()));
    }

    public static void methodExitEvent(EventContext context) {
        context.threadLocalWhenInTestAdapter().process(createMethodExit(context));
    }

    public static void monitorEnter(EventContext context, int position) {
        context.threadLocalWhenInTestAdapter().process(new RunAfter<>(new MonitorEnterEvent(
                context.methodId(), position),
                new SetObjectHashCode<>(context.object())));
    }

    public static void monitorExit(EventContext context,int position) {
        context.threadLocalWhenInTestAdapter().process(createMonitorExit(context,position));
    }

    public static RunAfter<MonitorExitEvent> createMonitorExit(EventContext context,int position) {
        return new RunAfter<>(new MonitorExitEvent(
                context.methodId(), position),
                new SetObjectHashCode<>(context.object()));
    }

    public static RunAfter<MethodExitEvent> createMethodExit(EventContext context) {
        return new RunAfter<>(new MethodExitEvent(context.methodId()),
                new SetFieldsNoOp<>());
    }

    public static void newTask(NewTaskContext enterExitContext) {
        enterExitContext.parallelizeFacade().newTask(
                enterExitContext.threadLocalWhenInTestAdapter().eventQueue(),
                enterExitContext.threadLocalWhenInTestAdapter().threadLocalForParallelize(),
                new ThreadWrapper(Thread.currentThread()));
        methodEnterEvent(enterExitContext);
    }

}
