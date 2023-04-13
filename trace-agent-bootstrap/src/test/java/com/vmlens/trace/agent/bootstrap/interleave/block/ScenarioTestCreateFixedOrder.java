package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadBegin;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScenarioTestCreateFixedOrder {

    @Test
    public void testThreadStartBegin() {
        // Given
        TLinkedList<TLinkableWrapper<InterleaveActionWithPositionFactory>> actualRun = new
                TLinkedList<>();
        actualRun.add(new TLinkableWrapper(new InterleaveActionWithPositionFactoryImpl(new ThreadStart(1),0)));
        actualRun.add(new TLinkableWrapper(new InterleaveActionWithPositionFactoryImpl(new ThreadBegin(),1)));

        LeftBeforeRight[] expectedFixedOrderArray = new LeftBeforeRight[1];
        expectedFixedOrderArray[0] = new LeftBeforeRight(new Position(0, 0), new Position(1, 0));
        AlternatingOrderContainer expectedContainer = new AlternatingOrderContainer(expectedFixedOrderArray, new AlternatingOrderElement[0]);

        // When
        AlternatingOrderContainerFactory factory = new AlternatingOrderContainerFactory();
        AlternatingOrderContainer container = factory.create(actualRun);

        // Then
        assertThat(container,is(expectedContainer));
    }


}
