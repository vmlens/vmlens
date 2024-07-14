package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.guineapig.DependentBlockElementGuineaPig;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class AddDependentBlocksToOrderArraysBuilderTest {

    @Test
    public void addBlocksIfStartsAlternatingOrderReturnsTrue() {
        // Expected
        AlternatingOrderElement expectedAlternatingOrderElement = new AlternatingOrderElement(
                lbr(0, 0, 1, 0),
                lbr(1, 0, 0, 0));

        // When
        OrderArraysBuilder builder = executeTest(1, 0, true);

        // Then
        assertThat(builder.alternatingOrderList().get(0).element, is(expectedAlternatingOrderElement));
    }

    @Test
    public void noBlocksAddedIfSameThreadIndex() {
        // When
        OrderArraysBuilder builder = executeTest(0, 1, true);

        // Then
        assertThat(builder.alternatingOrderList().size(), is(0));
    }

    @Test
    public void noBlocksAddedIfsAlternatingOrderReturnsFalse() {
        // When
        OrderArraysBuilder builder = executeTest(1, 0, false);

        // Then
        assertThat(builder.alternatingOrderList().size(), is(0));
    }

    private OrderArraysBuilder executeTest(int secondThreadIndex, int secondThreadPos, boolean startsAlternatingOrder) {
        // Given
        Object dependedBlockKey = new Object();
        DependentBlockElement dependentBlockElementGuineaPig = new DependentBlockElementGuineaPig(startsAlternatingOrder);
        ElementAndPosition firstEpos = ElementAndPosition.epos(dependentBlockElementGuineaPig, 0, 0);
        ElementAndPosition secondEpos = ElementAndPosition.epos(dependentBlockElementGuineaPig,
                secondThreadIndex,
                secondThreadPos);

        MapOfBlocks mapOfBlocks = new MapOfBlocks();
        mapOfBlocks.addDependent(dependedBlockKey, new DependentBlock(firstEpos, firstEpos));
        mapOfBlocks.addDependent(dependedBlockKey, new DependentBlock(secondEpos, secondEpos));

        AddDependentBlocksToOrderArraysBuilder add = new AddDependentBlocksToOrderArraysBuilder();
        OrderArraysBuilder builder = new OrderArraysBuilder();

        // When
        add.add(mapOfBlocks.dependentBlocks(), builder);

        return builder;
    }
}
