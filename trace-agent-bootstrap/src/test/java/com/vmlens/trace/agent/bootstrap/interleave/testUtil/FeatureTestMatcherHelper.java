package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;

class FeatureTestMatcherHelper {

    private final Set<LeftBeforeRight> currentFoundLeft = new HashSet<>();
    private final Set<LeftBeforeRight> expectedLeftBeforeRight;

    FeatureTestMatcherHelper(Set<LeftBeforeRight> expectedLeftBeforeRight) {
        this.expectedLeftBeforeRight = expectedLeftBeforeRight;
    }

    void executed(Position position) {
        for (LeftBeforeRight left : expectedLeftBeforeRight) {
            if (left.left.equals(position)) {
                currentFoundLeft.add(left);
            }
        }
        Set<LeftBeforeRight> currentFoundLeftView = new HashSet<>(currentFoundLeft);
        for (LeftBeforeRight right : currentFoundLeftView) {
            if (right.right.equals(position)) {
                currentFoundLeft.remove(right);
                expectedLeftBeforeRight.remove(right);
            }
        }
    }

    void fullfilled() {
        assertThat(expectedLeftBeforeRight, empty());
    }

}
