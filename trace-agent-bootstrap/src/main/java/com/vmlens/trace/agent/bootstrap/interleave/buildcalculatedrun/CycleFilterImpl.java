package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrayList;
import gnu.trove.map.hash.THashMap;
import gnu.trove.set.hash.THashSet;

import java.util.Iterator;

import static com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight.lbr;

/*
 * we map left to right to the cycle number
 * and the length of each cycle
 *
 * To be sure we use a set to check that each happens before only
 * occur once in the orderArrayList
 *
 * we throw an exception is there is more than one
 * we should not add a cycle twice since if the order contained a cycle it should be filtered
 * but we should check this and throw an exception if it happens
 *
 *
 */

public class CycleFilterImpl implements CycleFilter {

    private final THashMap<LeftBeforeRight, THashSet<Integer>> leftBeforeRightToCycles = new THashMap<>();
    private final THashMap<Integer,MaxAncCurrentCount> cycleIdToCount = new THashMap<>();
    private int nextCycleId;

    @Override
    public void addLeftBeforeRight(Position left, Position right, int cycleId) {
        MaxAncCurrentCount maxAndCurrentCount = cycleIdToCount.get(cycleId);
        if(maxAndCurrentCount == null) {
            cycleIdToCount.put(cycleId,new MaxAncCurrentCount());
        } else {
            maxAndCurrentCount.incrementMaxCount();
        }
        LeftBeforeRight lbr = lbr(left,right);
        THashSet<Integer> set = leftBeforeRightToCycles.get(lbr);
        if(set == null) {
            set = new THashSet<>();
            leftBeforeRightToCycles.put(lbr,set);
        } else {
            if(set.contains(cycleId)) {
                throw new RuntimeException("double added");
            }
        }
        set.add(cycleId);
    }

    @Override
    public int nextCycleId() {
      return nextCycleId;
    }

    @Override
    public void setNextCycleId(int nextCycleId) {
        this.nextCycleId = nextCycleId;
    }

    public boolean hasKnownCycle(OrderArrayList orderArrayList) {
        Iterator<LeftBeforeRight> iter = orderArrayList.iterator();
        while(iter.hasNext()) {
            LeftBeforeRight current = iter.next();
            THashSet<Integer> cycleSet = leftBeforeRightToCycles.get(current);
            if(cycleSet != null) {
                for(Integer cycleId : cycleSet) {
                    cycleIdToCount.get(cycleId).incrementCurrentCount();
                }
            }
        }
        boolean containsCycle = false;
        for(MaxAncCurrentCount entry : cycleIdToCount.values())    {
            containsCycle = containsCycle | entry.checkCurrentCountAndReset();
        }
        return containsCycle;
    }

}
