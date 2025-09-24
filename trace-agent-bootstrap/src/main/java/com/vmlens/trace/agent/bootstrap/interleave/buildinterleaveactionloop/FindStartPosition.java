package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;

public class FindStartPosition {

    private final NormalizeContext normalizeContext;

    public FindStartPosition(NormalizeContext normalizeContext) {
        this.normalizeContext = normalizeContext;
    }

    public Integer findStart(InterleaveAction[] array) {
        for( int end = 0 ; end < array.length; end++ ) {
            for( int start = 0 ; start < end; start++ ) {
                if(array[start].equalsNormalized(normalizeContext,array[end])) {
                    return start;
                }
            }
        }
        return null;
    }

}
