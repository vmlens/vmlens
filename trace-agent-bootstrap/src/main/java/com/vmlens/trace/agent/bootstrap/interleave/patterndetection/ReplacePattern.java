package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.NoOpInterleaveAction;

import static com.vmlens.trace.agent.bootstrap.interleave.patterndetection.DetectPattern.MIMiMUM_PATTERN_OCCURRENCE;

public class ReplacePattern {

    private final InterleaveAction[] interleaveActions;
    private final  PatternKeyAndCount[] patternKeyAndCounts;

    public ReplacePattern(InterleaveAction[] interleaveActions, PatternKeyAndCount[] patternKeyAndCounts) {
        this.interleaveActions = interleaveActions;
        this.patternKeyAndCounts = patternKeyAndCounts;
    }

    public InterleaveAction[] replace() {
       for(PatternKeyAndCount pattern : patternKeyAndCounts) {
           replaceOnePattern(pattern);
       }
        return interleaveActions;
    }

    private void replaceOnePattern(PatternKeyAndCount pattern ) {
        int len = pattern.length();
        int i = 0;
        while (i <= interleaveActions.length - len) {
            int count = 0;
            while (i + (count + 1) * len <= interleaveActions.length  &&
                    pattern.matches(i + count * len)) {
                count++;
            }
            if (count > MIMiMUM_PATTERN_OCCURRENCE) {
                /*
                 * we separate the count in 3 parts, the start, the middle and the end
                 * than we keep the start, e.g. start till length of patten and the middle,
                 * e.g. middle * length till middle * length + length and end
                 *
                 */
                int middle = count/2;
                int end = count * pattern.length();
                int middleStart = middle *  pattern.length();
                replaceWithNoop(i+ pattern.length()  , middleStart - 1);
                replaceWithNoop(middleStart + pattern.length() -1 , end - 1);
                i += pattern.length() * count;
            } else {
                i++;
            }
        }
    }

    private void replaceWithNoop(int start, int end) {
        for(int i = start; i < end; i++ ) {
            interleaveActions[i] = new NoOpInterleaveAction(interleaveActions[i].threadIndex());
        }
    }

}
