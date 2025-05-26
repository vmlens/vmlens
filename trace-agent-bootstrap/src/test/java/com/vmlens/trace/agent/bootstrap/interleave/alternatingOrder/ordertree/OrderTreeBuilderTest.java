package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class OrderTreeBuilderTest {


    @Test
    public void fixedOnly() {
        // Expected
        LeftBeforeRight fixed = lbr(1, 1, 2, 2);

        // Given
        OrderTreeBuilder builder = new OrderTreeBuilder();

        // When
        builder.addFixedOrder(fixed);
        OrderTree tree = builder.build();

        // Then
        assertThat(tree.iterator().hasNext(),is(false));
        assertThat(tree.fixedOrder()[0], is(fixed));
    }

    @Test
    public void eitherAndAlternating() {
        // Given
        OrderTreeBuilder builder = new OrderTreeBuilder();
        LeftBeforeRight either1A = lbr(1, 1, 2, 2);
        LeftBeforeRight either1B = lbr(6, 6, 2, 2);

        LeftBeforeRight either2A = lbr(1, 9, 2, 2);
        LeftBeforeRight either2B = lbr(6, 9, 2, 2);

        // When
         builder.start().either(new AlternativeTuple(
                            new AlternativeOneOrder(either1A),
                            new AlternativeOneOrder(either1B)),
                 new AlternativeTuple(
                            new AlternativeOneOrder(either2A),
                            new AlternativeOneOrder(either2B)));
         OrderTree tree = builder.build();
         OrderTreeIterator iterator = tree.iterator();

         CreateOrderContext createOrderContext = new CreateOrderContext();
         while(iterator.hasNext()) {
            iterator.advanceAndAddToOrder(createOrderContext,true);
         }

        // Then
        assertThat(createOrderContext.newOrder.size(),is(1));
        assertThat(createOrderContext.newOrder.get(0).element(),is(either1A));
    }

}
