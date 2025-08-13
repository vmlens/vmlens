package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class OrderArrayListTest {

    @Test
    public void resize() {
        // Given
        OrderArrayList list = new OrderArrayList(2,new LeftBeforeRight[0]);
        LeftBeforeRight order = lbr(1, 1, 0, 0);

        // When
        list.add(order);
        list.add(order);
        list.add(order);

        // Then
        assertThat(list.get(0),is((order)));
        assertThat(list.get(2),is((order)));
    }


}
