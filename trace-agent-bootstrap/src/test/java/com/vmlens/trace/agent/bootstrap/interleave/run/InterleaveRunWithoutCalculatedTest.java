package com.vmlens.trace.agent.bootstrap.interleave.run;

import gnu.trove.list.linked.TIntLinkedList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated.calculateActiveByPositionInRun;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class InterleaveRunWithoutCalculatedTest {

    @Test
    public void calculateActiveByPositionInRunTest() {
        TIntLinkedList indices = new TIntLinkedList();
        indices.add(5);
        indices.add(7);
        indices.add(9);

        assertThat(calculateActiveByPositionInRun(4,7,indices),is(true));
        assertThat(calculateActiveByPositionInRun(3,7,indices),is(false));
        assertThat(calculateActiveByPositionInRun(3,5,indices),is(true));
        assertThat(calculateActiveByPositionInRun(2,9,indices),is(true));
    }

}
