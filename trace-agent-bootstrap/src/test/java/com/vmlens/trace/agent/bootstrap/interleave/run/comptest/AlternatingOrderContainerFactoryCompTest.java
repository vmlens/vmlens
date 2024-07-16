package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.trace.agent.bootstrap.interleave.IntArray.intArray;
import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlternatingOrderContainerFactoryCompTest {

    @Test
    public void volatileField() {
        // Expected
        Set<IntArray> threadIndices = new HashSet<>();
        threadIndices.add(intArray(0, 1));
        threadIndices.add(intArray(1, 0));

        // Given
        VolatileFieldAccessTestBuilder f1 = VolatileFieldAccessTestBuilder.firstVolatileField();
        ActualRunTestBuilder builder = new ActualRunTestBuilder();

        PositionTestBuilder readPosition = new PositionTestBuilder();
        PositionTestBuilder writePosition = new PositionTestBuilder();

        builder.thread(
                f1.read(readPosition)
        );
        builder.thread(
                f1.write(writePosition)
        );

        TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = builder.build();

        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();

        // Expected
        LeftBeforeRight readBeforeWrite = lbr(readPosition.build(), writePosition.build());
        LeftBeforeRight writeBeforeRead = lbr(writePosition.build(), readPosition.build());
        Set<LeftBeforeRight> expectedOrder = new HashSet<>();
        expectedOrder.add(readBeforeWrite);
        expectedOrder.add(writeBeforeRead);

        // When
        AlternatingOrderContainer alternatingOrder = factory.create(actualRun);

        // Then
        ThreadIndexSetBuilder threadIndexSetBuilder = new ThreadIndexSetBuilder();
        threadIndexSetBuilder.execute(alternatingOrder);

        assertThat(threadIndexSetBuilder.executedTThreadIndexArray(), is(threadIndices));
        assertThat(threadIndexSetBuilder.count(), is(2));
    }

    @Test
    public void volatileReadTwoWrites() {
        // Expected
        Set<IntArray> threadIndices = new HashSet<>();
        threadIndices.add(intArray(0, 1, 1));
        threadIndices.add(intArray(1, 0, 1));
        threadIndices.add(intArray(1, 1, 0));

        // Given
        VolatileFieldAccessTestBuilder f1 = VolatileFieldAccessTestBuilder.firstVolatileField();
        ActualRunTestBuilder builder = new ActualRunTestBuilder();

        builder.thread(
                f1.read()
        );
        builder.thread(
                f1.write(),
                f1.write()
        );

        TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = builder.build();

        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();

        // When
        AlternatingOrderContainer alternatingOrder = factory.create(actualRun);

        // Then
        ThreadIndexSetBuilder threadIndexSetBuilder = new ThreadIndexSetBuilder();
        threadIndexSetBuilder.execute(alternatingOrder);

        assertThat(threadIndexSetBuilder.executedTThreadIndexArray(), is(threadIndices));
        assertThat(threadIndexSetBuilder.count(), is(3));

    }


}
