package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FixedAndAlternatingOrder {

    public final TLinkableWrapper<LeftBeforeRight>[] fixedOrder;
    public final TLinkableWrapper<AlternatingOrderElement>[] alternatingOrder;

    public FixedAndAlternatingOrder(TLinkableWrapper<LeftBeforeRight>[] fixedOrder, TLinkableWrapper<AlternatingOrderElement>[] alternatingOrder) {
        this.fixedOrder = fixedOrder;
        this.alternatingOrder = alternatingOrder;
    }
}
