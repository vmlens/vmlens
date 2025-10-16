package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.List;

public class CycleFilterBuildAlgorithm {

    private final CycleFilter cycleFilter;

    public CycleFilterBuildAlgorithm(CycleFilter cycleFilter) {
        this.cycleFilter = cycleFilter;
    }

    public void addCycles(List<List<Position>> cycles) {
        int currentCycleId = cycleFilter.nextCycleId();
        for(List<Position> current : cycles) {
            addCycle(current,currentCycleId);
            currentCycleId++;
        }
        cycleFilter.setNextCycleId(currentCycleId);
    }

    private void addCycle(List<Position> cycle,int cycleId) {
        Position start = null;
        Position left = null;
        for(Position current : cycle) {
            if(left != null) {
                if(left.threadIndex() != current.threadIndex) {
                    cycleFilter.addLeftBeforeRight(left, current, cycleId);
                }
            }
            if(start == null) {
                start = current;
            }
            left = current;
        }
        if(left.threadIndex() != start.threadIndex) {
            cycleFilter.addLeftBeforeRight(left,start,cycleId);
        }

    }

}
