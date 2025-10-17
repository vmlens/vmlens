package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CycleFilterMock implements CycleFilter {

    private final List<LeftBeforeRight> added = new LinkedList<>();
    private final int expectedCycleId;

    public CycleFilterMock(int expectedCycleId) {
        this.expectedCycleId = expectedCycleId;
    }

    @Override
    public void addLeftBeforeRight(Position left, Position right, int cycleId) {
        assertThat(cycleId,is(expectedCycleId));
        added.add(LeftBeforeRight.lbr(left,right));
    }

    @Override
    public int nextCycleId() {
        return 0;
    }

    @Override
    public void setNextCycleId(int next) {

    }

    public List<LeftBeforeRight> added() {
        return added;
    }
}
