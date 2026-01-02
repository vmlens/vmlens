package com.vmlens.report.overview;

import java.util.Comparator;

public class UITestLoopAndWarningComparator implements Comparator<UITestLoopAndWarning> {

    @Override
    public int compare(UITestLoopAndWarning first, UITestLoopAndWarning second) {
        return first.name().compareTo(second.name());
    }
}
