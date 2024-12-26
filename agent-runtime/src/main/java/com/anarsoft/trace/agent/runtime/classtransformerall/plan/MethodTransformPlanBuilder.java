package com.anarsoft.trace.agent.runtime.classtransformerall.plan;


import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper.wrap;


public class MethodTransformPlanBuilder {

    private final TLinkedList<TLinkableWrapper<PlanElement>> planElementList =
            new TLinkedList<>();

    public void add(PlanElement planElement) {
        planElementList.add(wrap(planElement));
    }

    public MethodTransformPlan build() {
        return new MethodTransformPlan(planElementList);
    }

    // Visible for Tests
    public TLinkedList<TLinkableWrapper<PlanElement>> planElementList() {
        return planElementList;
    }
}
