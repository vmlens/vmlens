package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsEmptyCollection.empty;

public class ParallelizeTestMatcher {
    private final Set<LeftBeforeRight> expectedLeftBeforeRight = new HashSet<>();
    private  Set<LeftBeforeRight> currentFoundLeft = new HashSet<>();
    private int expectedRuns;
    private int actualRuns;

    public void leftBeforeRight(Position left, Position right) {
        expectedLeftBeforeRight.add(new LeftBeforeRight(left,right));
    }

    public void runs(int i) {
        expectedRuns = i;
    }

    public void advance() {
        actualRuns++;
        currentFoundLeft = new HashSet<>();
    }
    public void executed(Position position) {
        for(LeftBeforeRight left :  expectedLeftBeforeRight) {
            if(left.left.equals(position)) {
                currentFoundLeft.add(left);
            }
        }
        for(LeftBeforeRight right :  currentFoundLeft) {
            if(right.right.equals(position)) {
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
