package com.vmlens.report.output;

import java.util.Comparator;

public class UILoopAndRunElementWithStacktraceLeafsComparator implements Comparator<UILoopAndRunElementWithStacktraceLeafs> {


    @Override
    public int compare(UILoopAndRunElementWithStacktraceLeafs first, UILoopAndRunElementWithStacktraceLeafs second) {
        return first.name().compareTo(second.name());
    }
}
