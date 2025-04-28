package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToElementList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.IntArray.intArray;
import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.IntArrayUtil.toIntArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;

public class CreateCalculatedRunTest {

    @Test
    public void circle() {
        // Given
        LeftBeforeRight[] order = new LeftBeforeRight[]{lbr(0, 0, 1, 0),
                lbr(1, 0, 0, 0)};
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0, 0));
        actualRun.add(pos(1, 0));
        CreateCalculatedRun createCalculatedRun = new CreateCalculatedRun(order, actualRun);

        // When
        CalculatedRun calculatedRun = createCalculatedRun.create();

        // Then
        assertThat(calculatedRun, is(nullValue()));
    }

    @Test
    public void complexCircle() {
        // Given
        LeftBeforeRight[] order = new LeftBeforeRight[]{lbr(0, 2, 1, 0),
                lbr(1, 1, 0, 1)};
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0, 0));
        actualRun.add(pos(0, 1));
        actualRun.add(pos(0, 2));
        actualRun.add(pos(1, 0));
        actualRun.add(pos(1, 1));
        actualRun.add(pos(1, 2));
        CreateCalculatedRun createCalculatedRun = new CreateCalculatedRun(order, actualRun);

        // When
        CalculatedRun calculatedRun = createCalculatedRun.create();

        // Then
        assertThat(calculatedRun, is(nullValue()));
    }

    @Test
    public void threeThreadIndex() {
        // Expected
        IntArray expectedIndices = intArray(0, 0, 1, 1, 2);

        // Given
        LeftBeforeRight[] order = new LeftBeforeRight[]{lbr(0, 1, 1, 0),
                lbr(1, 1, 2, 0)};
        ThreadIndexToElementList<Position> actualRun = new ThreadIndexToElementList<>();
        actualRun.add(pos(0, 0));
        actualRun.add(pos(0, 1));
        actualRun.add(pos(1, 0));
        actualRun.add(pos(1, 1));
        actualRun.add(pos(2, 0));
        CreateCalculatedRun createCalculatedRun = new CreateCalculatedRun(order, actualRun);

        // When
        CalculatedRun calculatedRun = createCalculatedRun.create();

        // Then
        IntArray array = toIntArray(calculatedRun.calculatedRunElementArray());
        assertThat(array, is(expectedIndices));

    }

}
