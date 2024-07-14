package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.AlternatingOrderContainerFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AlternatingOrderContainerFactoryCompTest {

    @Test
    public void volatileField() {
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
        AssertAlternatingOrder assertAlternatingOrder = new AssertAlternatingOrder(expectedOrder);
        assertAlternatingOrder.assertAlternatingOrder(alternatingOrder);

        assertThat(assertAlternatingOrder.expectedLeftBeforeRightIsEmpty(), is(true));
        assertThat(assertAlternatingOrder.count(), is(2));
    }


}
