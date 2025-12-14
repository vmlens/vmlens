package com.vmlens.report.builder;

import com.vmlens.report.input.RunElement;

import java.util.Comparator;

public class RunElementByRunPositionComparator implements Comparator<RunElement> {
    @Override
    public int compare(RunElement left, RunElement rigth) {
        if(left.runId() != rigth.runId()) {
            return Integer.compare(left.runId(), rigth.runId());
        }
        return Integer.compare(left.runPosition(), rigth.runPosition());
    }
}
