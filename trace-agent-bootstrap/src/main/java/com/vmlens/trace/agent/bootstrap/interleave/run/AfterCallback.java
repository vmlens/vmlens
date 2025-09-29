package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.PluginEventOnly;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;

import static com.vmlens.trace.agent.bootstrap.exception.Message.NON_VOLATILE_LOOP_MESSAGE;
import static com.vmlens.trace.agent.bootstrap.exception.Message.SYNC_ACTION_LOOP_MESSAGE;

public class AfterCallback {

    private final InterleaveRun interleaveRun;
    private final SendEvent sendEvent;

    public AfterCallback(InterleaveRun interleaveRun, SendEvent sendEvent) {
        this.interleaveRun = interleaveRun;
        this.sendEvent = sendEvent;
    }

    public void onNonVolatileLoop() {
        sendEvent.sendMessage(NON_VOLATILE_LOOP_MESSAGE);
    }

    public void onSynchronizedActionLoop() {
        interleaveRun.setHasLoop();
        sendEvent.sendMessage(SYNC_ACTION_LOOP_MESSAGE);
    }

    public void process(ProcessEventContext context, PluginEventOnly pluginEventOnly) {
        interleaveRun.process(context, pluginEventOnly);
    }

    public int process(ProcessEventContext context, InterleaveActionFactory interleaveActionFactory) {
        return interleaveRun.process(context, interleaveActionFactory);
    }
}
