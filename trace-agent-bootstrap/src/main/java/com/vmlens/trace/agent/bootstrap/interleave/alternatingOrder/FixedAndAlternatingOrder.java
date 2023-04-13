package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class FixedAndAlternatingOrder {

    public final TLinkableWrapper<LeftBeforeRightOld>[] fixedOrder;
    public final TLinkableWrapper<AlternatingOrderElementOld>[] alternatingOrder;

    public FixedAndAlternatingOrder(TLinkableWrapper<LeftBeforeRightOld>[] fixedOrder, TLinkableWrapper<AlternatingOrderElementOld>[] alternatingOrder) {
        this.fixedOrder = fixedOrder;
        this.alternatingOrder = alternatingOrder;
    }
}
