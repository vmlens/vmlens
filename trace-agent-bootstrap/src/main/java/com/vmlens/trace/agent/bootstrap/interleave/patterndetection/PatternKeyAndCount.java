package com.vmlens.trace.agent.bootstrap.interleave.patterndetection;

public class PatternKeyAndCount {

    private final PatternKey patternKey;
    private final int count;

    public PatternKeyAndCount(PatternKey patternKey, int count) {
        this.patternKey = patternKey;
        this.count = count;
    }

    public int count() {
        return count;
    }

    public int length() {
        return patternKey.length();
    }

    public boolean matches(int otherStart) {
        return patternKey.samePattern(otherStart);
    }

    @Override
    public String toString() {
        return "PatternKeyAndCount{" +
                "patternKey=" + patternKey +
                ", count=" + count +
                '}';
    }

    // For Tests
    PatternKey patternKey() {
        return patternKey;
    }
}
