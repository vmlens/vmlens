package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import java.util.Comparator;

public class PatternKeyAndCountComparator implements Comparator<PatternKeyAndCount> {
    @Override
    public int compare(PatternKeyAndCount first, PatternKeyAndCount second) {
        if(first.count() != second.count()) {
            return Integer.compare(second.count(),first.count());
        }
        return  Integer.compare(second.length(),first.length());
    }
}
