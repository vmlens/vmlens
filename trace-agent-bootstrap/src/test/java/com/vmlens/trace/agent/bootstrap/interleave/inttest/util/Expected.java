package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.IntArrayUtil.toIntArray;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class Expected {

    private final boolean isExpected;
    private final List<ExpectedElement> expectedElements;

    public Expected(boolean isExpected,
                    List<ExpectedElement> expectedElements) {
        this.isExpected = isExpected;
        this.expectedElements = expectedElements;
    }

    public Potential asPotential() {
        return new Potential(expectedElements.stream().map(ExpectedElement::asPotential).collect(Collectors.toList()));
    }

    public void check(Map<ExpectedElement,Integer> fulfilled,List<Position[]> executed) {
        boolean notFulfilled = false;
        for(ExpectedElement expectedElement : expectedElements) {
            if(! fulfilled.containsKey(expectedElement)) {
                notFulfilled = true;
                System.err.println("not executed:" + expectedElement);
            }
        }
        if(notFulfilled)  {
            for(Position[] array : executed ) {
                System.err.println(toIntArray(array));
            }
        }
        assertThat(notFulfilled,is(false));
    }
}
