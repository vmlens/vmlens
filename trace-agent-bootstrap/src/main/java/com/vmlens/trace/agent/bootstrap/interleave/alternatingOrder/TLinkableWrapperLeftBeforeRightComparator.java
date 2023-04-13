package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.util.Comparator;

public class TLinkableWrapperLeftBeforeRightComparator  implements Comparator<TLinkableWrapper<LeftBeforeRightOld>>  {

    @Override
    public int compare(TLinkableWrapper<LeftBeforeRightOld> left, TLinkableWrapper<LeftBeforeRightOld> right) {
        return left.element.compareTo(right.element);
    }
}
