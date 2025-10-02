package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.Either;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;
import java.util.Map;

import static com.vmlens.trace.agent.bootstrap.Either.left;
import static com.vmlens.trace.agent.bootstrap.Either.right;

public class CycleDetectionAdapter {

    private final CalculatedRunBuilder calculatedRunBuilder;
    private THashMap<LeftBeforeRight,MutableBoolean> previousOrder = null;

    public CycleDetectionAdapter(CalculatedRunBuilder calculatedRunBuilder) {
        this.calculatedRunBuilder = calculatedRunBuilder;
    }

    public Either<CalculatedRun, THashSet<Position>> build(OrderArrayList orderArrayList,
                                                           THashSet<Position> previousStartingPoints) {
        if(previousOrder == null) {
            initial(orderArrayList);
        } else {
            update(orderArrayList);
        }

        THashSet<Position> newStartingPoint = calculatedRunBuilder.buildCycles(previousStartingPoints);
        if(newStartingPoint.isEmpty()) {
            CalculatedRun run = calculatedRunBuilder.build();
            if(run != null) {
                return left(run);
            }
            return right(calculatedRunBuilder.buildAllCycles());
        } else {
            return right(newStartingPoint);
        }
    }


    private void initial(OrderArrayList orderArrayList) {
        for(int i = 0 ; i <  orderArrayList.length(); i++ ) {
            calculatedRunBuilder.addOrder(orderArrayList.get(i));
        }
        previousOrder = new THashMap<>();
        Iterator<LeftBeforeRight> iter = orderArrayList.iterator();
        while(iter.hasNext()) {
            previousOrder.put(iter.next(), new MutableBoolean());
        }
    }

    private void update(OrderArrayList orderArrayList) {
        THashMap<LeftBeforeRight,MutableBoolean> newlyAdded = new THashMap<>();
        Iterator<LeftBeforeRight> iter = orderArrayList.iterator();
        while(iter.hasNext()) {
            LeftBeforeRight current = iter.next();
            newlyAdded.put(current,new MutableBoolean());
            MutableBoolean contains = previousOrder.get(current);
            if(contains == null) {
                calculatedRunBuilder.addOrder(current);
            } else {
                contains.set();
            }
        }

        for(Map.Entry<LeftBeforeRight,MutableBoolean> entry :   previousOrder.entrySet()) {
            if( ! entry.getValue().isSet()) {
                calculatedRunBuilder.removeOrder(entry.getKey());
            }
        }
        previousOrder = newlyAdded;
    }

}
