package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.interleave.IntArray;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;

import java.util.*;

import static com.vmlens.trace.agent.bootstrap.interleave.IntArray.wrap;

public class ThreadIndexSetBuilder {

    private int count;
    private Set<IntArray> executedTThreadIndexArray = new HashSet<>();

    public void execute(AlternatingOrderContainer alternatingOrderContainer) {
        Iterator<CalculatedRun> iter = alternatingOrderContainer.iterator();
        while (iter.hasNext()) {
            CalculatedRun calculatedRun = iter.next();
            List<Integer> executedThreadIndex = new LinkedList<>();
            if (calculatedRun != null) {
                count++;
                for (Position pos : calculatedRun.calculatedRunElementArray()) {
                    executedThreadIndex.add(pos.threadIndex);
                }
                executedTThreadIndexArray.add(toArray(executedThreadIndex));
            }
        }
    }

    public int count() {
        return count;
    }

    public Set<IntArray> executedTThreadIndexArray() {
        return executedTThreadIndexArray;
    }

    private IntArray toArray(List<Integer> list) {
        int[] array = new int[list.size()];
        int index = 0;
        Iterator<Integer> iter = list.iterator();
        while (iter.hasNext()) {
            array[index] = iter.next();
            index++;
        }
        return wrap(array);
    }


}
