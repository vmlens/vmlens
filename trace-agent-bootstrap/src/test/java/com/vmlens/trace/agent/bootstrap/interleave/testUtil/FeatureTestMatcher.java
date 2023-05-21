package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;


public class FeatureTestMatcher {
    private final Set<LeftBeforeRight> expectedLeftBeforeRight = new HashSet<>();
    private Set<LeftBeforeRight> currentFoundLeft = new HashSet<>();
    private Map<Integer, Integer> threadIndexToPosition = new HashMap<>();
    private int expectedRuns;
    private int actualRuns;

    public void leftBeforeRight(Position left, Position right) {
        expectedLeftBeforeRight.add(new LeftBeforeRight(left, right));
    }

    public void both(Position left, Position right) {
        expectedLeftBeforeRight.add(new LeftBeforeRight(left, right));
        expectedLeftBeforeRight.add(new LeftBeforeRight(right, left));
    }

    public void runs(int i) {
        expectedRuns = i;
    }

    public void advance() {
        actualRuns++;
        currentFoundLeft = new HashSet<>();
        threadIndexToPosition = new HashMap<>();
    }

    public void executed(int threadIndex) {
        int currentPos = 0;
        if (threadIndexToPosition.containsKey(threadIndex)) {
            currentPos = threadIndexToPosition.get(threadIndex);
        }
        threadIndexToPosition.put(threadIndex, currentPos + 1);
        Position position = new Position(threadIndex, currentPos);
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

    public void assertExpectedResults() {
        assertThat(expectedLeftBeforeRight,empty());
        assertThat(actualRuns,is(expectedRuns));
    }

}
