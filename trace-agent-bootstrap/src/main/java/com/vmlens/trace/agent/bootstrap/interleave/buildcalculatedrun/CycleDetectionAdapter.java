package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import gnu.trove.map.hash.THashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CycleDetectionAdapter {

    private final CalculatedRunBuilder calculatedRunBuilder;
    private final CycleFilterImpl cycleFilter = new CycleFilterImpl();
    private THashMap<LeftBeforeRight,MutableBoolean> previousOrder = null;

    public CycleDetectionAdapter(CalculatedRunBuilder calculatedRunBuilder) {
        this.calculatedRunBuilder = calculatedRunBuilder;
    }

    public CalculatedRun build(OrderArrayList orderArrayList) {
        if(previousOrder == null) {
            initial(orderArrayList);
        } else {
            // first check if we have an already known cycle
            if(cycleFilter.hasKnownCycle(orderArrayList)) {
                return null;
            }
            update(orderArrayList);
        }

        List<List<Position>> cycles = calculatedRunBuilder.buildCycles();
        if(cycles.isEmpty()) {
            return calculatedRunBuilder.build();
        } else {
            new CycleFilterBuildAlgorithm(cycleFilter).addCycles(cycles);
            return null;
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
