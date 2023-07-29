package com.vmlens.trace.agent.bootstrap.callback.field;

public class FieldAccessCallbackImpl {
    private final Strategy[] strategyArray;

    public FieldAccessCallbackImpl() {
        strategyArray = new Strategy[10];
        strategyArray[0] = new StrategyImplNonVolatile(false);
        strategyArray[1] = new StrategyImplNonVolatile(true);

        strategyArray[2] = new StrategyImplVolatile(false);
        strategyArray[3] = new StrategyImplVolatile(true);
    }


    public void field_access_static(int fieldId, int methodId, int callbackId) {
        strategyArray[callbackId].field_access_static(fieldId, methodId);
    }


    public Long field_access_from_generated_method(Object orig, Long offset, int
            fieldId, int methodId, int callbackId) {
        if (orig == null) {
            return offset;
        }
        Long calculatedOffset = offset;
        if (calculatedOffset == null) {
            calculatedOffset = UpdateObjectState.getFieldOffset(orig.getClass());
        }

        strategyArray[callbackId].field_access_generated(orig, calculatedOffset, fieldId, methodId);
        return calculatedOffset;
    }

    public void field_access(Object orig, int fieldId, int methodId, int callbackId) {
        if (orig == null) {
            return;
        }
        strategyArray[callbackId].field_access_default(orig, fieldId, methodId);
    }


}
