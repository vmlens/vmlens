package com.vmlens.trace.agent.bootstrap.callback.threadlocal;

public class InMethodIdAndPosition {

    private final int inMethodId;
    private final int position;

    public InMethodIdAndPosition(int inMethodId, int position) {
        this.inMethodId = inMethodId;
        this.position = position;
    }

    public int inMethodId() {
        return inMethodId;
    }

    public int position() {
        return position;
    }
}
