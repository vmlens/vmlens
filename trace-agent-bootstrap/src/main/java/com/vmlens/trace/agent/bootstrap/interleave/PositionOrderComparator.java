package com.vmlens.trace.agent.bootstrap.interleave;

import java.util.Comparator;

public class PositionOrderComparator implements Comparator<PositionOrder> {
    @Override
    public int compare(PositionOrder first, PositionOrder second) {
        int pos = first.left().compareTo(second.left());
        if(pos != 0) {
            return pos;
        }
        return Boolean.compare(second.isAfter(),first.isAfter());
    }
}
