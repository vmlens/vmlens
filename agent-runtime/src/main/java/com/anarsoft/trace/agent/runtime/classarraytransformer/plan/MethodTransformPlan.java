package com.anarsoft.trace.agent.runtime.classarraytransformer.plan;

import com.anarsoft.trace.agent.runtime.classarraytransformer.MethodCallbackFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

public class MethodTransformPlan {

    private final TLinkedList<TLinkableWrapper<PlanElement>> planElementList;
    private int index;

    public MethodTransformPlan(TLinkedList<TLinkableWrapper<PlanElement>> planElementList) {
        this.planElementList = planElementList;
    }

    public void apply(MethodCallbackFactory callbackCallFactory) {
        planElementList.get(index).element.afterOperation(callbackCallFactory);
        index++;

    }
}
