package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.util.Comparator;

public class AlternatingOrderElementComparator implements Comparator<TLinkableWrapper<AlternatingOrderElementOld>> {

    @Override
    public int compare(TLinkableWrapper<AlternatingOrderElementOld> left,
                       TLinkableWrapper<AlternatingOrderElementOld> right) {
        return left.element.leftBeforeRight.compareTo( right.element.leftBeforeRight);
    }
}
