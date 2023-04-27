package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.WithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;

public class ElementAndPosition<ELEMENT> implements WithPosition, WithThreadIndex {
    private final ELEMENT element;
    private final Position position;
    public ElementAndPosition(ELEMENT element, Position position) {
        this.element = element;
        this.position = position;
    }
    public ELEMENT element() {
        return element;
    }

    public Position position() {
        return position;
    }

    @Override
    public int threadIndex() {
        return position.threadIndex();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ElementAndPosition<?> that = (ElementAndPosition<?>) o;

        if (!element.equals(that.element)) return false;
        return position.equals(that.position);
    }

    @Override
    public int hashCode() {
        int result = element.hashCode();
        result = 31 * result + position.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ElementAndPosition{" +
                "element=" + element +
                ", position=" + position +
                '}';
    }
}
