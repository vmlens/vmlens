package com.vmlens.trace.agent.bootstrap.ordermap;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderMapTest {

    @Test
    public void objectHashCode() {
        // Given
        long hashCode = 456L;
        OrderMap<Long> orderMap = new OrderMap<>();

        // When
        int order = orderMap.getAndIncrementOrder(hashCode);

        // Then
        assertThat(order, is(0));

        // When
        order = orderMap.getAndIncrementOrder(hashCode);

        // Then
        assertThat(order, is(1));
    }
}
