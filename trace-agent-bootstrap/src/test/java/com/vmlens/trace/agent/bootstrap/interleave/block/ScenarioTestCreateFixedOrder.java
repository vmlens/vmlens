package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.InterleaveActionWithPositionFactoryImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.InterleaveTestBuilder;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.MAIN_THREAD_INDEX;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScenarioTestCreateFixedOrder {

    // ToDo wieder aktivieren
    @Test
    public void testThreadStartBegin() {
        // Given
        TestBuilderResultBlockBuilder testBuilderResultBlockBuilder = new TestBuilderResultBlockBuilder();
        InterleaveTestBuilder builder = new InterleaveTestBuilder(testBuilderResultBlockBuilder);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.startThread(1);
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        OrderArraysFactory factory = new OrderArraysFactory();

        // When
        OrderArrays orderArrays = factory.create(testBuilderResultBlockBuilder.blockBuilderList());

        // Expected
        LeftBeforeRight[] expectedFixedOrderArray = new LeftBeforeRight[1];
        expectedFixedOrderArray[0] = new LeftBeforeRight(new Position(0, 0), new Position(1, 0));
        OrderArrays expectedOrderArrays = new OrderArrays(expectedFixedOrderArray, new AlternatingOrderElement[0]);
        // Then
        assertThat(orderArrays,is(expectedOrderArrays));
    }


}
