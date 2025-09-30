package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatedRunBuilderTest {

    @Test
    public void oneOrder() {
        // Given
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0,0));
        actualRun.add(pos(0,1));
        actualRun.add(pos(1,0));
        actualRun.add(pos(1,1));

        CalculatedRunBuilder calculatedRunBuilder = new CalculatedRunBuilder(actualRun);
        calculatedRunBuilder.addOrder(LeftBeforeRight.lbr(0,1,1,0));

        // When
        CalculatedRun calculatedRun = calculatedRunBuilder.build();

        // Then
        assertThat(calculatedRun.calculatedRunElementArray(),is(new Position[]{
                pos(0,0),
                pos(0,1),
                pos(1,0),
                pos(1,1)}));
    }

    @Test
    public void testCycle() {
        // Given
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0,0));
        actualRun.add(pos(0,1));
        actualRun.add(pos(1,0));
        actualRun.add(pos(1,1));

        CalculatedRunBuilder calculatedRunBuilder = new CalculatedRunBuilder(actualRun);
        calculatedRunBuilder.addOrder(LeftBeforeRight.lbr(0,1,1,0));
        calculatedRunBuilder.addOrder(LeftBeforeRight.lbr(1,0,0,1));
        // When
        CalculatedRun calculatedRun = calculatedRunBuilder.build();

        // Then
        assertThat(calculatedRun,is(nullValue()));
    }


}
