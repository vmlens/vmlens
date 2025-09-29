package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.runelementtype.objecthashcodemap.ObjectHashCodeMap;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

public class BarrierOperation implements RunElementType  {

    private final BarrierOperationType barrierOperationType;
    private final BarrierKey barrierKey;
    private ObjectHashCodeMap objectHashCodeMap;

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
        return objectHashCodeMap.asUiString(barrierKey.objectHashcode());
    }

    @Override
    public void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex) {
        this.objectHashCodeMap = objectHashCodeMap;
        objectHashCodeMap.add(barrierKey.objectHashcode(),threadIndex);
    }

}
