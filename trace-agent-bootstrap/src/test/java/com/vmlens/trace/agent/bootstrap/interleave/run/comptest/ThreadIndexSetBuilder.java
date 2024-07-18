package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.IntArrayUtil.toIntArray;

public class ThreadIndexSetBuilder {

    private int count;
    private Set<IntArray> executedTThreadIndexArray = new HashSet<>();

    public void execute(AlternatingOrderContainer alternatingOrderContainer) {
        Iterator<CalculatedRun> iter = alternatingOrderContainer.iterator();
        while (iter.hasNext()) {
            CalculatedRun calculatedRun = iter.next();
            if (calculatedRun != null) {
                count++;
                executedTThreadIndexArray
                        .add(toIntArray(calculatedRun.calculatedRunElementArray()));
            }
        }
    }
    public int count() {
        return count;
    }
    public Set<IntArray> executedTThreadIndexArray() {
        return executedTThreadIndexArray;
    }

}
