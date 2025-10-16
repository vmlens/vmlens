package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import org.junit.Test;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;
import static com.vmlens.trace.agent.bootstrap.interleave.Position.pos;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CycleFilterImplTest {

    @Test
    public void knownCycleGetsDetectec() {
        // Given
        CycleFilterImpl cycleFilterImpl = new CycleFilterImpl();
        cycleFilterImpl.addLeftBeforeRight(pos(0,1) , pos(1,0) , 0);

        OrderArrayList orderArrayList = new OrderArrayList(new LeftBeforeRight[]{});
        orderArrayList.add(lbr(pos(0,1) , pos(1,0)));

        // When Then
        assertThat(cycleFilterImpl.hasKnownCycle(orderArrayList),is(true));
    }


}
