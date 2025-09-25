package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;

public class AfterCallback {

    private final InterleaveRun interleaveRun;
    private final SendEvent sendEvent;

    public AfterCallback(InterleaveRun interleaveRun, SendEvent sendEvent) {
        this.interleaveRun = interleaveRun;
        this.sendEvent = sendEvent;
    }
}
