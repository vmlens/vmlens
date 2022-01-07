package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.domain.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CalculatedRun {
    private final TLinkedList<TLinkableWrapper<Position>> positions;

    public CalculatedRun(TLinkedList<TLinkableWrapper<Position>> positions) {
        this.positions = positions;
    }

    public String toString() {
        boolean isFirst = true;
        String result = "";
        for (TLinkableWrapper<Position> position : positions) {
            if (!isFirst) {
                result += ",";
            }
            isFirst = false;
            result += position.element.threadIndex;
        }
        return result;
    }

}
