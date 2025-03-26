package com.vmlens.trace.agent.bootstrap.event.warning;

import com.vmlens.trace.agent.bootstrap.event.gen.LoopWarningEventGen;

public class LoopWarningEvent extends LoopWarningEventGen implements Warning {

    public LoopWarningEvent(int loopId, int runId, int messageId) {
        this.loopId = loopId;
        this.runId = runId;
        this.messageId = messageId;
    }
}
