package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class FeatureTestMatcher {
    private final Set<LeftBeforeRight> expectedLeftBeforeRight;
    private final HashSet<LeftBeforeRight> alwaysExpected;
    private final int expectedRuns;
    private Map<Integer, Integer> threadIndexToPosition = new HashMap<>();
    private int actualRuns;
    private FeatureTestMatcherHelper helperForAlways;
    private FeatureTestMatcherHelper helperForExpected;

    public FeatureTestMatcher(Set<LeftBeforeRight> expectedLeftBeforeRight,
                              HashSet<LeftBeforeRight> alwaysExpected,
                              int expectedRuns) {
        this.expectedLeftBeforeRight = expectedLeftBeforeRight;
        this.alwaysExpected = alwaysExpected;
        this.expectedRuns = expectedRuns;
        this.helperForAlways = new FeatureTestMatcherHelper((Set<LeftBeforeRight>) alwaysExpected.clone());
        this.helperForExpected = new FeatureTestMatcherHelper(expectedLeftBeforeRight);
    }

    public void advance() {
        actualRuns++;
        threadIndexToPosition = new HashMap<>();
        helperForAlways.fullfilled();
        helperForAlways = new FeatureTestMatcherHelper((Set<LeftBeforeRight>) alwaysExpected.clone());
    }

    public void executed(int threadIndex) {
        int currentPos = 0;
        if (threadIndexToPosition.containsKey(threadIndex)) {
            currentPos = threadIndexToPosition.get(threadIndex);
        }
        threadIndexToPosition.put(threadIndex, currentPos + 1);
        Position position = new Position(threadIndex, currentPos);
        executed(position);
    }

    private void executed(Position position) {
        helperForAlways.executed(position);
        helperForExpected.executed(position);
    }

    public void assertExpectedResults() {
        helperForAlways.fullfilled();
        helperForExpected.fullfilled();
        assertThat(actualRuns, is(expectedRuns));
    }

    public void executed(Position[] calculatedRunElementArray) {
        for (Position pos : calculatedRunElementArray) {
            executed(pos);
        }
        advance();
    }
}
