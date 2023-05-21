package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class TestData {

    private final TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun;
    private final AlternatingOrderContainer alternatingOrderContainer;
    private final FeatureTestMatcher calculatedRunMatcher;

    public TestData(TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun,
                    AlternatingOrderContainer alternatingOrderContainer,
                    FeatureTestMatcher calculatedRunMatcher) {
        this.actualRun = actualRun;
        this.alternatingOrderContainer = alternatingOrderContainer;
        this.calculatedRunMatcher = calculatedRunMatcher;
    }

    public TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun() {
        return actualRun;
    }

    public AlternatingOrderContainer alternatingOrderContainer() {
        return alternatingOrderContainer;
    }

    public FeatureTestMatcher calculatedRunMatcher() {
        return calculatedRunMatcher;
    }
}
