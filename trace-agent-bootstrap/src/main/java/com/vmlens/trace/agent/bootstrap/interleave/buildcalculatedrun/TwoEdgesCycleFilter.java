package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.PositionOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;

/*
 *   (0,3) < (1,7)
 *   (0,2) > (1,4)
 *    o.k.
 *
 *   (0,2) > (1,9)
 *   (0,3) < (1,7)
 *    n.o.k
 *
 *   we get a list with inverse
 *   sort it by thread index and position in thread
 *
 * if left after right set minimum value if greater than current minimum value
 * if left before right check that value is greater than current minimum value
 *
 */
public class TwoEdgesCycleFilter {

    public boolean hasCycle(OrderArrayList orderArrayList) {
        PositionOrder[] array = orderArrayList.withInverse();
        int maxThreadIndex = array[array.length - 1].left().threadIndex;
        int currentThreadIndex = array[0].left().threadIndex;
        TwoEdgesCycleFilterState twoEdgesCycleFilterState = new TwoEdgesCycleFilterState(maxThreadIndex);
        for(PositionOrder positionOrder : array) {
            if(positionOrder.left().threadIndex != currentThreadIndex) {
                currentThreadIndex = positionOrder.left().threadIndex;
                twoEdgesCycleFilterState = new TwoEdgesCycleFilterState(maxThreadIndex);
            }

            if(positionOrder.checkHasCycleOrSetMinimum(twoEdgesCycleFilterState)) {
                return true;
            }
        }
        return false;
    }


}
