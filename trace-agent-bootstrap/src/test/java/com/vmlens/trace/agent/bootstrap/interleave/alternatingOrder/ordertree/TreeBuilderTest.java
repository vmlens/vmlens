package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.Choice;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilder;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TreeBuilderTest {

    @Test
    public void either() {
        // Given
        TreeBuilder builder = new TreeBuilder();
        LeftBeforeRight either1A = lbr(1, 1, 2, 2);
        LeftBeforeRight either1B = lbr(6, 6, 2, 2);

        // When
         builder.start().either(new AlternativeOneOrder(either1A),new AlternativeOneOrder(either1B));
         OrderTree tree = builder.build();
         OrderTreeIterator iterator = tree.iterator();

         CreateOrderContext createOrderContext = new CreateOrderContext();
         while(iterator.hasNext()) {
            iterator.advanceAndAddToOrder(createOrderContext,true);
         }

        // Then
        assertThat(createOrderContext.newOrder.size(),is(1));
        assertThat(createOrderContext.newOrder.get(0).element(),is(either1A));
        assertThat(tree.length(),is(1));
    }

    @Test
    public void choice() {
        // Given
        TreeBuilder builder = new TreeBuilder();
        LeftBeforeRight either1A = lbr(1, 1, 2, 2);
        LeftBeforeRight either1B = lbr(6, 6, 2, 2);

        LeftBeforeRight nextOrder = lbr(0, 55, 2, 2);

        AlternativeOneOrder firstEither = new AlternativeOneOrder(either1A);
        AlternativeOneOrder nextEither = new AlternativeOneOrder(nextOrder);

        // When
        Choice choice = builder.start().choice();
        choice.alternativeA().either(firstEither,new AlternativeNoOrder(false));
        choice.alternativeB().either(new AlternativeOneOrder(either1B),new AlternativeNoOrder(true))
                .either(new AlternativeOneOrder(either1B),new AlternativeNoOrder(true));
        choice.next().either(nextEither,new AlternativeOneOrder(nextOrder));

        OrderTree tree = builder.build();

        // Then
        assertThat(tree.start(),instanceOf(TwoChildrenNode.class));
        TwoChildrenNode twoChildrenNode = (TwoChildrenNode) tree.start();

        assertThat(twoChildrenNode.firstAlternative(),instanceOf(SingleChildNode.class));
        SingleChildNode singleChildNode = (SingleChildNode) twoChildrenNode.firstAlternative();
        assertThat(singleChildNode.firstAlternative(),is(firstEither));

        SingleChildNode addedElement = (SingleChildNode) singleChildNode.next();
        assertThat(addedElement.firstAlternative(),is(new AlternativeNoOrder(true)));
        assertThat(addedElement.secondAlternative(),is(new AlternativeNoOrder(false)));

        SingleChildNode nextElement = (SingleChildNode) addedElement.next();
        assertThat(nextElement.firstAlternative(),is(nextEither));
        assertThat(tree.length(),is(4));
    }

}
