package com.vmlens.report.output;

import java.util.Comparator;

public class UISummaryElementComparator implements Comparator<UISummaryElement> {
    @Override
    public int compare(UISummaryElement left, UISummaryElement right) {
        return Integer.compare(right.count(),left.count());
    }
}
