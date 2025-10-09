package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

import static com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop.DetectPattern.matches;

public class PatternKey {

    private final InterleaveAction[] interleaveActions;
    private final int hashCode;
    private final int start;
    private final int length;


    public PatternKey(InterleaveAction[] interleaveActions, int hashCode , int start, int length) {
        this.interleaveActions = interleaveActions;
        this.hashCode = hashCode;
        this.start = start;
        this.length = length;

    }

    public int length() {
        return length;
    }

    public boolean samePattern(int otherStart) {
        return matches(interleaveActions,start,otherStart,length);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        PatternKey that = (PatternKey) object;
        if(length != that.length) {
            return false;
        }
        return matches(interleaveActions,start,that.start,length);
    }

    @Override
    public int hashCode() {
        return hashCode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ; i < length; i++) {
            builder.append(interleaveActions[i+start].toString());
        }
        return builder.toString();
    }

}
