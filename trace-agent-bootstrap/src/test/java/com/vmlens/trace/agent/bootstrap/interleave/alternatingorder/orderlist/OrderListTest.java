package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.PermutationIterator;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun.OrderArrayListFactory;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopMessageFactory;
import org.junit.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.mockito.Mockito.mock;

public class OrderListTest {

    @Test
    public void avoidCycle() {
        // Given
        LeftBeforeRight firstA = lbr(0,0 , 1 ,1);
        LeftBeforeRight firstB = lbr(1,1 , 0 ,0);

        LeftBeforeRight secondA = lbr(2,2 , 3 ,3);
        LeftBeforeRight secondB = lbr(3,3 , 2 ,2);

        ListBuilder listBuilder = new ListBuilder();
        listBuilder.start().either(
                        new AlternativeOneOrder(firstA),
                        new AlternativeOneOrder(firstB))
                            .either(
                        new AlternativeOneOrder(secondA),
                        new AlternativeOneOrder(secondB));

        OrderList tree = listBuilder.build( interleaveLoopContext());

        // When
        OrderCycle[] cycles = new OrderCycle[]{  new OrderCycle(firstB, secondA) };
        tree.avoidCycles(cycles);

        List<List<LeftBeforeRight>> actual = process(tree);

        // Then
        assertThat(actual, containsInAnyOrder(
                List.of(firstA, secondA),
                List.of(firstA,secondB),
                List.of(firstB, secondB)
        ));
    }

    private InterleaveLoopContext  interleaveLoopContext() {
        InterleaveLoopMessageFactory loopMessageFactory = mock(InterleaveLoopMessageFactory.class);
        return new InterleaveLoopContext(-1, 50 , -1 , -1,false,loopMessageFactory);
    }

    private List<List<LeftBeforeRight>> process(OrderList tree) {
        List<List<LeftBeforeRight>>  result = new LinkedList<>();
        PermutationIterator permutationIterator = new PermutationIterator(tree.length());
        OrderArrayListFactory orderArrayListFactory = new OrderArrayListFactory(new LeftBeforeRight[0]);
        while(permutationIterator.hasNext()) {
            OrderListIterator orderListIterator = tree.createIteratorAndResetOrderCycles();
            OrderArrayList order = orderArrayListFactory.create(orderListIterator, permutationIterator);
            if(order != null) {
                Iterator<LeftBeforeRight> orderIter = order.iterator();
                List<LeftBeforeRight> oneResult = new LinkedList<>();
                result.add(oneResult);
                while(orderIter.hasNext()) {
                    oneResult.add(orderIter.next());
                }
            }
        }
        return result;
    }

}
