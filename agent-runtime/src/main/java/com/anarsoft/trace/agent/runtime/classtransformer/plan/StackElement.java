package com.anarsoft.trace.agent.runtime.classtransformer.plan;

public class StackElement {

    // is be null for new opcode
    private final PlanElement planElement;
    private PlanElement forwardTo;

    public StackElement(PlanElement planElement) {
        this.planElement = planElement;
    }

    public StackElement() {
        this.planElement = null;
    }

    public void addApplyAfterOperation(ApplyAfterOperation applyAfterOperation) {
        if (forwardTo != null) {
            forwardTo.addApplyAfterOperation(applyAfterOperation);
        } else {
            planElement.addApplyAfterOperation(applyAfterOperation);
        }

    }

    public void setForwardTo(PlanElement forwardTo) {
        this.forwardTo = forwardTo;
        if (planElement != null) {
            planElement.forwardTo(forwardTo);
        }
    }

}
