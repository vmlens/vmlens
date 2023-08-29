package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.FIRST_WORKER_THREAD_INDEX;
import static com.vmlens.trace.agent.bootstrap.interleave.testUtil.FeatureTestBuilder.MAIN_THREAD_INDEX;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class TestCreateAlternatingOrder {

    @Test
    public void testVolatileFields() {
        // Given
        ResultTestBuilderForBlock resultTestBuilderForBlock = new ResultTestBuilderForBlock();
        FeatureTestBuilder builder = new FeatureTestBuilder(resultTestBuilderForBlock);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();
        OrderArraysFactory factory = new OrderArraysFactory();

        // When
        OrderArrays orderArrays = factory.create(resultTestBuilderForBlock.blockBuilderList(),
                mock(ThreadIndexToMaxPosition.class));

        // Expected
        AlternatingOrderElement[] alternatingOrder = new AlternatingOrderElement[1];
        alternatingOrder[0] = new AlternatingOrderElement(lbr(read, write), lbr(write, read));
        OrderArrays expectedOrderArrays = new OrderArrays(new LeftBeforeRight[0],alternatingOrder);

        // Then
        assertThat(orderArrays,is(expectedOrderArrays));

    }


}
