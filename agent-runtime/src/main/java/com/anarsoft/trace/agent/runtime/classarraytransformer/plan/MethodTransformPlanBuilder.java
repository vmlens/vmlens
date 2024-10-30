package com.anarsoft.trace.agent.runtime.classarraytransformer.plan;


import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;


public class MethodTransformPlanBuilder {

    private final TLinkedList<TLinkableWrapper<PlanElement>> planElementList =
            new TLinkedList<>();

    public void add(PlanElement planElement) {
        planElementList.add(wrap(planElement));
    }

    // Visible for Tests
    public TLinkedList<TLinkableWrapper<PlanElement>> planElementList() {
        return planElementList;
    }
}
