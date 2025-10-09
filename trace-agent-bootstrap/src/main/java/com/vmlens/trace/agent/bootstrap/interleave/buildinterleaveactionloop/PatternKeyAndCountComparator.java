package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import java.util.Comparator;

public class PatternKeyAndCountComparator implements Comparator<PatternKeyAndCount> {
    @Override
    public int compare(PatternKeyAndCount first, PatternKeyAndCount second) {
        if(first.length() != second.length()) {
            return Integer.compare(second.length(),first.length());
        }

        return  Integer.compare(second.count(),first.count());
    }
}
