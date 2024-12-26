package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.trace.agent.bootstrap.interleave.IntArray.intArray;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlternatingOrderContainerFactoryIntTest {

    @Test
    public void volatileField() {
        // Expected
        Set<IntArray> threadIndices = new HashSet<>();
        threadIndices.add(intArray(0, 1));
        threadIndices.add(intArray(1, 0));

        // Given
        VolatileFieldAccessTestBuilder f1 = VolatileFieldAccessTestBuilder.firstVolatileField();
        ActualRunTestBuilder builder = new ActualRunTestBuilder();


        builder.thread(
                f1.read()
        );
        builder.thread(
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

    @Test
    public void volatileFieldAndMonitor() {
        // Expected
        Set<IntArray> threadIndices = new HashSet<>();
        threadIndices.add(intArray(0, 0, 0, 1, 1, 1));
        threadIndices.add(intArray(1, 1, 1, 0, 0, 0));

        // Given
        VolatileFieldAccessTestBuilder f1 = VolatileFieldAccessTestBuilder.firstVolatileField();
        LockOrMonitorTestBuilder mx = LockOrMonitorTestBuilder.firstMonitor();

        ActualRunTestBuilder builder = new ActualRunTestBuilder();


        builder.thread(
                mx.enter(),
                f1.read(),
                mx.exit()
        );
        builder.thread(
                mx.enter(),
                f1.write(),
                mx.exit()
        );

        TLinkedList<TLinkableWrapper<InterleaveAction>> actualRun = builder.build();

        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();

        // When
        AlternatingOrderContainer alternatingOrder = factory.create(actualRun);

        // Then
        ThreadIndexSetBuilder threadIndexSetBuilder = new ThreadIndexSetBuilder();
        threadIndexSetBuilder.execute(alternatingOrder);

        assertThat(threadIndexSetBuilder.executedTThreadIndexArray(), is(threadIndices));
        assertThat(threadIndexSetBuilder.count(), is(2));
    }


}
