package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;
import java.util.List;

public class Cycle {

    private final THashMap<Position,MutableBoolean> partOfCycle;

    private Cycle(THashMap<Position, MutableBoolean> partOfCycle) {
        this.partOfCycle = partOfCycle;
    }

    public static Cycle create(List<List<Position>> cycleList, Iterator<LeftBeforeRight> iterator) {
        THashSet<Position> cycleFilter = new THashSet<>();
        for(List<Position>  list : cycleList ) {
            for(Position pos : list) {
                cycleFilter.add(pos);
            }
        }
        THashMap<Position,MutableBoolean> partOfCycle = new THashMap<>();
        while(iterator.hasNext()) {
            LeftBeforeRight lbr = iterator.next();
            if(cycleFilter.contains(lbr.left)) {
                partOfCycle.put(lbr.left, new MutableBoolean());
            }
            if(cycleFilter.contains(lbr.right)) {
                partOfCycle.put(lbr.right, new MutableBoolean());
            }
        }

        return new Cycle(partOfCycle);
    }

    /*  we store all alternating position in a map
     *  for the new order we check which position are no more contained in the map
     *  for those positions we check if they are part of a cycle and if
     *  yes we return cycleChanged = true
     *
     *  Basically we need both the cycle and the orders which led to the cycle since some
     *  part of the cycle might be related to fix orders
     *
     *  so when building the map to check we only take those which are part of an order and which
     *  were actually part of the current order
     *
     */
    public boolean cycleChanged(Iterator<LeftBeforeRight> alternatingOrder) {
        while(alternatingOrder.hasNext()) {
            LeftBeforeRight current = alternatingOrder.next();
            MutableBoolean left = partOfCycle.get(current.left);
            if(left != null) {
                left.set();
            }
            MutableBoolean right = partOfCycle.get(current.right);
            if(right != null) {
                right.set();
            }
        }

        Iterator<MutableBoolean> values = partOfCycle.values().iterator();
        while(values.hasNext()) {
            MutableBoolean current = values.next();
            if( ! current.isSet()) {
                return true;
            }
        }

        return false;
    }

}
