package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.HashSet;
import java.util.Set;

public class FeatureTestMatcherBuilder {

    private final Set<LeftBeforeRight> expectedLeftBeforeRight = new HashSet<>();
    private final HashSet<LeftBeforeRight> alwaysExpected = new HashSet<>();
    private int expectedRuns;

    public void runs(int i) {
        expectedRuns = i;
    }

    public void always(Position left, Position right) {
        alwaysExpected.add(new LeftBeforeRight(left, right));
    }

    public void leftBeforeRight(Position left, Position right) {
        expectedLeftBeforeRight.add(new LeftBeforeRight(left, right));
    }

    public void both(Position left, Position right) {
        expectedLeftBeforeRight.add(new LeftBeforeRight(left, right));
        expectedLeftBeforeRight.add(new LeftBeforeRight(right, left));
    }

    public FeatureTestMatcher build() {
        return new FeatureTestMatcher(expectedLeftBeforeRight, alwaysExpected, expectedRuns);
    }

}
