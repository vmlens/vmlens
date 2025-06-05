package com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class DependentOperationAndPosition<ELEMENT> {

    private final Position position;
    private final ELEMENT element;

    public DependentOperationAndPosition(Position position, ELEMENT element) {
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
