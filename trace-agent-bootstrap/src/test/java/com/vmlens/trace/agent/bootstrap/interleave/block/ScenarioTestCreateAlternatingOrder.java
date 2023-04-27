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

public class ScenarioTestCreateAlternatingOrder {

    @Test
    public void testVolatileFields() {
        // Given
        TestBuilderResultBlockBuilder testBuilderResultBlockBuilder = new TestBuilderResultBlockBuilder();
        InterleaveTestBuilder builder = new InterleaveTestBuilder(testBuilderResultBlockBuilder);
        builder.beginThread(MAIN_THREAD_INDEX);
        Position read = builder.readFirstVolatileField();
        builder.beginThread(FIRST_WORKER_THREAD_INDEX);
        Position write = builder.writeFirstVolatileField();
        OrderArraysFactory factory = new OrderArraysFactory();

        // When
        OrderArrays orderArrays = factory.create(testBuilderResultBlockBuilder.blockBuilderList());

        // Expected
        AlternatingOrderElement[] alternatingOrder = new AlternatingOrderElement[1];
        alternatingOrder[0] = new AlternatingOrderElement(new LeftBeforeRight(read,write),new LeftBeforeRight(write,read));
        OrderArrays expectedOrderArrays = new OrderArrays(new LeftBeforeRight[0],alternatingOrder);

        // Then
        assertThat(orderArrays,is(expectedOrderArrays));

    }


}
