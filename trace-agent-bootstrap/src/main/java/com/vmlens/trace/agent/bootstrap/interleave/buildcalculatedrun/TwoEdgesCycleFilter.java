package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Pair;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.PositionOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.TIntSet;
import gnu.trove.set.hash.THashSet;
import gnu.trove.set.hash.TIntHashSet;

import java.util.Iterator;


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

    private final THashMap<LeftBeforeRight, TIntSet> leftBeforeRightToCycles = new THashMap<>();
    private int maxCycleId = 0;

    public boolean hasCycle(OrderArrayList orderArrayList) {
        if(hasExistingCycles(orderArrayList)) {
            return true;
        }

        PositionOrder[] array = orderArrayList.withInverse();
        int maxThreadIndex = array[array.length - 1].left().threadIndex;
        int currentThreadIndex = array[0].left().threadIndex;
        TwoEdgesCycleFilterState twoEdgesCycleFilterState = new TwoEdgesCycleFilterState(maxThreadIndex);
        for(PositionOrder positionOrder : array) {
            if(positionOrder.left().threadIndex != currentThreadIndex) {
                currentThreadIndex = positionOrder.left().threadIndex;
                twoEdgesCycleFilterState = new TwoEdgesCycleFilterState(maxThreadIndex);
            }

            Pair<LeftBeforeRight, LeftBeforeRight> cycle = positionOrder.checkHasCycleOrSetMinimum(twoEdgesCycleFilterState);
            if(cycle != null) {
                TIntSet firstSet = leftBeforeRightToCycles.get(cycle.getLeft());
                if(firstSet == null) {
                    firstSet = new TIntHashSet();
                    leftBeforeRightToCycles.put(cycle.getLeft(), firstSet);
                }
                firstSet.add(maxCycleId);

                TIntSet secondSet = leftBeforeRightToCycles.get(cycle.getRight());
                if(secondSet == null) {
                    secondSet = new TIntHashSet();
                    leftBeforeRightToCycles.put(cycle.getRight(), secondSet);
                }
                secondSet.add(maxCycleId);
                maxCycleId++;
                return true;
            }
        }
        return false;
    }


    private boolean hasExistingCycles(OrderArrayList orderArrayList) {
        if(! leftBeforeRightToCycles.isEmpty()) {
            TIntSet foundCycles = new TIntHashSet();
            THashSet<LeftBeforeRight> alreadyProcessed = new THashSet<>();
            Iterator<LeftBeforeRight> iter = orderArrayList.iterator();
            while(iter.hasNext()) {
                LeftBeforeRight current = iter.next();
                if(! alreadyProcessed.contains(current)) {
                    alreadyProcessed.add(current);
                    TIntSet cycles = leftBeforeRightToCycles.get(current);
                    if(cycles != null) {
                        TIntIterator cycleIter = cycles.iterator();
                        while(cycleIter.hasNext()) {
                            int id = cycleIter.next();
                            if(foundCycles.contains(id)) {
                                return true;
                            }
                            foundCycles.add(id);
                        }
                    }
                }
            }
        }
        return false;
    }


}
