package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public class BarrierOperation implements RunElementType  {

    private final BarrierOperationType barrierOperationType;
    private final BarrierKey barrierKey;

    public BarrierOperation(BarrierOperationType barrierOperationType, BarrierKey barrierKey) {
        this.barrierOperationType = barrierOperationType;
        this.barrierKey = barrierKey;
    }

    @Override
    public String asString(DescriptionContext context) {
        BarrierKeyTextBuilder barrierKeyTextBuilder = new BarrierKeyTextBuilder();
        barrierKey.accept(barrierKeyTextBuilder);
        return barrierOperationType.text() + " " + barrierKeyTextBuilder.build();
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        // No description needed
    }


}
