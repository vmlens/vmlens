package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

public class CycleFilterBuildAlgorithmTest {

    @Test
    public void simpleCycle() {
        // Cycle
        List<LeftBeforeRight> cycle = new LinkedList<>();
        cycle.add(LeftBeforeRight.lbr(0,1,1,0));
        cycle.add(LeftBeforeRight.lbr(1,0,0,1));

        // Given
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0,0));
        actualRun.add(pos(0,1));
        actualRun.add(pos(1,0));
        actualRun.add(pos(1,1));

        CycleFilterMock cycleFilterMock = new CycleFilterMock(0);
        CycleFilterBuildAlgorithm cycleFilterBuildAlgorithm = new CycleFilterBuildAlgorithm(cycleFilterMock);
        CalculatedRunBuilder calculatedRunBuilder = buildCalculatedRunBuilder(cycle, actualRun);

        // When
        List<List<Position>> cycles = calculatedRunBuilder.buildCycles();
        cycleFilterBuildAlgorithm.addCycles(cycles);

        // Then
        assertThat(cycleFilterMock.added(), containsInAnyOrder(cycle.toArray()));
    }

    @Test
    public void cycleWithLbrInSameIndex() {
        // Cycle
        List<LeftBeforeRight> cycle = new LinkedList<>();
        cycle.add(LeftBeforeRight.lbr(0,1,1,0));
        cycle.add(LeftBeforeRight.lbr(1,1,0,0));

        // Given
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0,0));
        actualRun.add(pos(0,1));
        actualRun.add(pos(1,0));
        actualRun.add(pos(1,1));

        CycleFilterMock cycleFilterMock = new CycleFilterMock(0);
        CycleFilterBuildAlgorithm cycleFilterBuildAlgorithm = new CycleFilterBuildAlgorithm(cycleFilterMock);
        CalculatedRunBuilder calculatedRunBuilder = buildCalculatedRunBuilder(cycle, actualRun);

        // When
        List<List<Position>> cycles = calculatedRunBuilder.buildCycles();
        cycleFilterBuildAlgorithm.addCycles(cycles);

        // Then
        assertThat(cycleFilterMock.added(), containsInAnyOrder(cycle.toArray()));
    }

    private CalculatedRunBuilder buildCalculatedRunBuilder(List<LeftBeforeRight> cycle,
                                                           ThreadIndexToElementList<Position> actualRun) {
        CalculatedRunBuilder calculatedRunBuilder = new CalculatedRunBuilder(actualRun);
        for(LeftBeforeRight current : cycle) {
            calculatedRunBuilder.addOrder(current);
        }
        return calculatedRunBuilder;
    }

}
