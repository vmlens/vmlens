package com.vmlens.trace.agent.bootstrap.testFixture;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcher;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestMatcherBuilder;

public class TestData {

    private ResultTestBuilder resultTestBuilder;
    private AlternatingOrderContainer alternatingOrderContainer;
    private FeatureTestMatcher featureTestMatcher;

    public TestData() {
    }

    public ResultTestBuilder resultTestBuilder() {
        return resultTestBuilder;
    }

    public TestData setResultTestBuilder(ResultTestBuilder resultTestBuilder) {
        this.resultTestBuilder = resultTestBuilder;
        return this;
    }

    public AlternatingOrderContainer alternatingOrderContainer() {
        return alternatingOrderContainer;
    }

    public TestData setAlternatingOrderContainer(AlternatingOrderContainer alternatingOrderContainer) {
        this.alternatingOrderContainer = alternatingOrderContainer;
        return this;
    }

    public FeatureTestMatcher featureTestMatcher() {
        return featureTestMatcher;
    }

    public TestData setFeatureTestMatcher(FeatureTestMatcher featureTestMatcher) {
        this.featureTestMatcher = featureTestMatcher;
        return this;
    }


}
