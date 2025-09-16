package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class BarrierOperationAndPosition<ELEMENT> {

    private final Position position;
    private final ELEMENT element;

    public BarrierOperationAndPosition(Position position, ELEMENT element) {
        this.position = position;
        this.element = element;
    }

    public Position position() {
        return position;
    }

    public ELEMENT element() {
        return element;
    }
}
