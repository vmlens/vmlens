package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.InterleaveTestBuilder;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeTestBuilder.MAIN_THREAD_INDEX;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCreateFixedOrder {

    @Test
    public void testThreadStart() {
        // Given
        BlockBuilderTestBuilderResult blockBuilderTestBuilderResult = new BlockBuilderTestBuilderResult();
        InterleaveTestBuilder builder = new InterleaveTestBuilder(blockBuilderTestBuilderResult);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.startThread(FIRST_WORKER_THREAD_INDEX);
        OrderArraysFactory factory = new OrderArraysFactory();

        // When
        OrderArrays orderArrays = factory.create(blockBuilderTestBuilderResult.blockBuilderList(),mock(ThreadIndexToMaxPosition.class));

        // Expected
        LeftBeforeRight[] expectedFixedOrderArray = new LeftBeforeRight[1];
        expectedFixedOrderArray[0] = new LeftBeforeRight(new Position(0, 0), new Position(1, 0));
        OrderArrays expectedOrderArrays = new OrderArrays(expectedFixedOrderArray, new AlternatingOrderElement[0]);
        // Then
        assertThat(orderArrays,is(expectedOrderArrays));
    }

    @Test
    public void testThreadJoin() {
        // Given
        BlockBuilderTestBuilderResult blockBuilderTestBuilderResult = new BlockBuilderTestBuilderResult();
        InterleaveTestBuilder builder = new InterleaveTestBuilder(blockBuilderTestBuilderResult);
        builder.beginThread(MAIN_THREAD_INDEX);
        builder.joinThread(FIRST_WORKER_THREAD_INDEX);
        OrderArraysFactory factory = new OrderArraysFactory();
        ThreadIndexToMaxPosition threadIndexToMaxPosition = mock(ThreadIndexToMaxPosition.class);
        when(threadIndexToMaxPosition.getPositionAtThreadIndex(1)).thenReturn(3);
        // When
        OrderArrays orderArrays = factory.create(blockBuilderTestBuilderResult.blockBuilderList(),threadIndexToMaxPosition);

        // Expected
        LeftBeforeRight[] expectedFixedOrderArray = new LeftBeforeRight[1];
        expectedFixedOrderArray[0] = new LeftBeforeRight(new Position(1, 3), new Position(0, 0));
        OrderArrays expectedOrderArrays = new OrderArrays(expectedFixedOrderArray, new AlternatingOrderElement[0]);
        // Then
        assertThat(orderArrays,is(expectedOrderArrays));
    }

}
