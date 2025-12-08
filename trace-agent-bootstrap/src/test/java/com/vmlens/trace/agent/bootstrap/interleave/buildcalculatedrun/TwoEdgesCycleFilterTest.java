package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class TwoEdgesCycleFilterTest {

    @Test
    public void cycleFromDoc() {
        OrderArrayListTestBuilder orderArrayListTestBuilder = new OrderArrayListTestBuilder();
        //  (0,2) > (1,9)
        orderArrayListTestBuilder.lbr(1 ,9 , 0,2  );
        //  (0,3) < (1,7)
        orderArrayListTestBuilder.lbr(0 ,3 , 1,7  );
        OrderArrayList orderArrayList = orderArrayListTestBuilder.build();
        TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();

        // When and Then
        assertThat(twoEdgesCycleFilter.hasCycle(orderArrayList) , is(true));
    }

    @Test
    public void noCycleFromDoc() {
        // Given
        OrderArrayListTestBuilder orderArrayListTestBuilder = new OrderArrayListTestBuilder();
        //  (0,2) > (1,4)
        orderArrayListTestBuilder.lbr(1 ,4 , 0,2  );
        //  (0,3) < (1,7)
        orderArrayListTestBuilder.lbr(0 ,3 , 1,7  );
        OrderArrayList orderArrayList = orderArrayListTestBuilder.build();
        TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();

        // When and Then
        assertThat(twoEdgesCycleFilter.hasCycle(orderArrayList) , is(false));
    }

    @Test
    public void multipleVolatileSmallNoCycle() {
        OrderArrayList orderArrayList = new MultipleVolatileSmallNoCycle().build();
        TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();

        // When and Then
        assertThat(twoEdgesCycleFilter.hasCycle(orderArrayList) , is(false));
    }

    @Test
    public void multipleVolatileSmallCycle() {
        OrderArrayList orderArrayList = new MultipleVolatileSmallCycle().build();
        TwoEdgesCycleFilter twoEdgesCycleFilter = new TwoEdgesCycleFilter();

        // When and Then
        assertThat(twoEdgesCycleFilter.hasCycle(orderArrayList) , is(true));
    }


}
