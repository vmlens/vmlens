package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

import java.util.Arrays;

public class CalculatedRun {

    private final Position[] calculatedRunElementArray;

    public CalculatedRun(Position[] calculatedRunElementArray) {
        this.calculatedRunElementArray = calculatedRunElementArray;
    }

    public Position[] calculatedRunElementArray() {
        return calculatedRunElementArray;
    }

    public CalculatedRun copy() {
        return new  CalculatedRun(Arrays.copyOf(calculatedRunElementArray,
                calculatedRunElementArray.length));
    }

    @Override
    public String toString() {
        return "CalculatedRun{" +
                "calculatedRunElementArray=" + Arrays.toString(calculatedRunElementArray) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        CalculatedRun that = (CalculatedRun) o;
        return Arrays.equals(calculatedRunElementArray, that.calculatedRunElementArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(calculatedRunElementArray);
    }


}
