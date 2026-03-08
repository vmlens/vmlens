package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ChoiceTest {

    /**
     * if we have a choice we need to have both alternatives
     * is one alternative has more than one element we need to calculate all combinations:
     *
     */
    @Test
    public void choice() {
        // Given
        LeftBeforeRight firstA = lbr(0,0 , 1 ,1);
        LeftBeforeRight firstB = lbr(1,1 , 0 ,0);

        LeftBeforeRight secondA = lbr(2,2 , 3 ,3);
        LeftBeforeRight secondB = lbr(3,3 , 2 ,2);

        Choice choice = new Choice(null);

        choice.alternativeA().either(
                new AlternativeOneOrder(firstA),
                new AlternativeOneOrder(firstB));
        choice.alternativeB().either(
                new AlternativeOneOrder(secondA),
                new AlternativeOneOrder(secondB));

        // When
        OrderListElement listElement = choice.build();

        // Then
        assertThat(listElement.numberOfAlternatives(),is(4));
    }

}
