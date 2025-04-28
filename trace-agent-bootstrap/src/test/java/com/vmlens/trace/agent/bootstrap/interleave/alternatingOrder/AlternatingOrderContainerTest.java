package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlwaysEnabled;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AlternatingOrderContainerTest {

    @Test
    public void testFixedEqualsIndependentOfSort() {
        // Given
        AlternatingOrderContainer first = createFixed(0, 1);
        AlternatingOrderContainer second = createFixed(1, 0);
        // When
        boolean equals = first.equals(second);
        // Then
        assertThat(equals, is(true));
    }

    @Test
    public void testAlternatingOrderEqualsIndependentOfSort() {
        // Given
        AlternatingOrderContainer first = createAlternatingOrder(0,1);
        AlternatingOrderContainer second = createAlternatingOrder(1,0);
        // When
        boolean equals = first.equals(second);
        // Then
        assertThat(equals, is(true));
    }
    private AlternatingOrderContainer createFixed(int indexA,int indexB ) {
        LeftBeforeRight[] firstOrderArray = new LeftBeforeRight[2];
        firstOrderArray[indexA] = leftBeforeRightOne();
        firstOrderArray[indexB] = leftBeforeRightTwo();
        return  new AlternatingOrderContainer(new OrderArrays(firstOrderArray, new AlternatingOrderElement[0],new LeftBeforeRightPair[0]),null);
    }
    private AlternatingOrderContainer createAlternatingOrder(int indexA,int indexB ) {
        AlternatingOrderElement[] alternatingOrderElementArray = new AlternatingOrderElement[2];
        alternatingOrderElementArray[indexA] = new AlternatingOrderElement(new AlwaysEnabled(),leftBeforeRightOne(),leftBeforeRightTwo());
        alternatingOrderElementArray[indexB] = new AlternatingOrderElement(new AlwaysEnabled(),leftBeforeRightThree(),leftBeforeRightFour());
        return  new AlternatingOrderContainer(new OrderArrays(new LeftBeforeRight[0], alternatingOrderElementArray,new LeftBeforeRightPair[0]),null);
    }
    private LeftBeforeRight leftBeforeRightOne() {
        return new LeftBeforeRight(new Position(0, 0), new Position(1, 0));
    }

    private LeftBeforeRight leftBeforeRightTwo() {
        return new LeftBeforeRight(new Position(2, 1), new Position(3, 0));
    }

    private LeftBeforeRight leftBeforeRightThree() {
        return new LeftBeforeRight(new Position(0, 5), new Position(1, 9));
    }

    private LeftBeforeRight leftBeforeRightFour() {
        return new LeftBeforeRight(new Position(2, 8), new Position(3, 30));
    }


}
