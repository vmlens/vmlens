package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.event.warning.LoopWarningEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;


public class AfterCallback {

    private final InterleaveRun interleaveRun;
    private final SendEvent sendEvent;

    public AfterCallback(InterleaveRun interleaveRun, SendEvent sendEvent) {
        this.interleaveRun = interleaveRun;
        this.sendEvent = sendEvent;
    }

    public void onNonVolatileLoop() {
        sendEvent.sendMessage(LoopWarningEvent.nonVolatileLoop());
    }

    public void onSynchronizedActionLoop() {
        interleaveRun.setHasLoop();
        sendEvent.sendMessage(LoopWarningEvent.syncActionLoop());
    }

    public void process(ProcessEventContext context, PluginEventOnly pluginEventOnly) {
        interleaveRun.process(context, pluginEventOnly);
    }

    public int process(ProcessEventContext context, InterleaveActionFactory interleaveActionFactory) {
        return interleaveRun.process(context, interleaveActionFactory);
    }

    public int synchronizationActionsLoopThreshold() {
        return interleaveRun.interleaveLoopContext().synchronizationActionsLoopThreshold();
    }

    public int unsynchronizedOperationsLoopThreshold() {
        return interleaveRun.interleaveLoopContext().unsynchronizedOperationsLoopThreshold();
    }


}
