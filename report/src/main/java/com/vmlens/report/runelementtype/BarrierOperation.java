package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

public class BarrierOperation implements RunElementType  {

    private final BarrierOperationType barrierOperationType;
    private final BarrierKey barrierKey;

    public BarrierOperation(BarrierOperationType barrierOperationType, BarrierKey barrierKey) {
        this.barrierOperationType = barrierOperationType;
        this.barrierKey = barrierKey;
    }

    @Override
    public String operation() {
        BarrierKeyTextBuilder barrierKeyTextBuilder = new BarrierKeyTextBuilder();
        barrierKey.accept(barrierKeyTextBuilder);
        return barrierKeyTextBuilder.build() + " " + barrierOperationType.text();
    }

    @Override
    public String element(DescriptionContext context) {
        return "";
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        // No description needed
    }

    @Override
    public String object(DescriptionContext context) {
        return "" +  barrierKey.objectHashcode();
    }
}
