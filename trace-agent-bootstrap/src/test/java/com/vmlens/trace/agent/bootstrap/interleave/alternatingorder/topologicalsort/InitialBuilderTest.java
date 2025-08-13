package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.topologicalsort;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToElementList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;

public class InitialBuilderTest {

    @Test
    public void order() {
        // Expected
        Position[] array = new Position[2];
        array[0] = pos(0, 0);
        array[1] = pos(1, 0);

        // Given
        InitialBuilder builder = buildInitialBuilder();
        builder.addOrder(LeftBeforeRight.lbr(pos(0, 0),pos(1, 0)));

        // When
        Pair<CalculatedRun, UpdateBuilder> result = builder.build();

        // Then
        // Fixme
        //assertThat(result.getLeft(),is(new CalculatedRun(array)));

    }

    @Test
    public void cycle() {

    }

    private InitialBuilder buildInitialBuilder() {
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0, 0));
        actualRun.add(pos(1, 0));
        return new InitialBuilder(actualRun);
    }

}
