package com.vmlens.report.builder;

import com.vmlens.report.element.RunElement;

import java.util.Comparator;

public class RunElementByRunPositionComparator implements Comparator<RunElement> {
    @Override
    public int compare(RunElement left, RunElement rigth) {
        return Integer.compare(left.runPosition(), rigth.runPosition());
    }
}
