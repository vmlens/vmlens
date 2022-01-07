package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import java.util.Comparator;

public class TLinkableWrapperLeftBeforeRightComparator  implements Comparator<TLinkableWrapper<LeftBeforeRight>>  {

    @Override
    public int compare(TLinkableWrapper<LeftBeforeRight> left, TLinkableWrapper<LeftBeforeRight> right) {
        return left.element.compareTo(right.element);
    }
}
