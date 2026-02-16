package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListBuilderTest {

    @Test
    public void buildEither() {
        // Given
        LeftBeforeRight firstA = lbr(0,0 , 1 ,1);
        LeftBeforeRight firstB = lbr(1,1 , 0 ,0);

        LeftBeforeRight secondA = lbr(2,2 , 3 ,3);
        LeftBeforeRight secondB = lbr(3,3 , 2 ,2);

        ListBuilder listBuilder = new ListBuilder();

        // When
        listBuilder.start()
                .either(
                        new AlternativeOneOrder(firstA),
                        new AlternativeOneOrder(firstB))
                .either(
                        new AlternativeOneOrder(secondA),
                        new AlternativeOneOrder(secondB));

        TLinkedList<TLinkableWrapper<OrderListElement>> orderList = listBuilder.buildList();

        // Then
        assertThat(orderList.size(),is(2));
        assertThat(orderList.get(0).element().numberOfAlternatives(),is(2));
        assertThat(orderList.get(1).element().numberOfAlternatives(),is(2));

    }


}
