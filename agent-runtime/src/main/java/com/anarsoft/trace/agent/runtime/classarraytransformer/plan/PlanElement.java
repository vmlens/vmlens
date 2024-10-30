package com.anarsoft.trace.agent.runtime.classarraytransformer.plan;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import org.objectweb.asm.MethodVisitor;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class PlanElement {

    private final TLinkedList<TLinkableWrapper<ApplyAfterOperation>> afterOperationList = new TLinkedList<>();

    // visible for test
    public void addApplyAfterOperation(ApplyAfterOperation applyAfterOperation) {
        afterOperationList.add(wrap(applyAfterOperation));
    }

    void forwardTo(PlanElement forward) {
        for (TLinkableWrapper<ApplyAfterOperation> alem : afterOperationList) {
            forward.addApplyAfterOperation(alem.element);
        }
        afterOperationList.clear();
    }

    public void afterOperation(MethodVisitor methodVisitor) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PlanElement that = (PlanElement) o;
        return afterOperationList.equals(that.afterOperationList);
    }

    @Override
    public int hashCode() {
        return afterOperationList.hashCode();
    }
}
