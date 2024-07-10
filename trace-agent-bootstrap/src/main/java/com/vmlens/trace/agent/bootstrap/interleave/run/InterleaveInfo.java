package com.vmlens.trace.agent.bootstrap.interleave.run;

public class InterleaveInfo {

    private final int runPosition;

    public InterleaveInfo(int runPosition) {
        this.runPosition = runPosition;
    }

    public int runPosition() {
        return runPosition;
    }
}
