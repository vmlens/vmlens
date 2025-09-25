package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

public class FindStartPosition {

    public Integer findStart(InterleaveAction[] array) {
        for( int end = 0 ; end < array.length; end++ ) {
            for( int start = 0 ; start < end; start++ ) {
                if(array[start].equalsNormalized(array[end])) {
                    return start;
                }
            }
        }
        return null;
    }

}
