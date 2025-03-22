package com.vmlens.report;

public class VerifyResult {

    private final int failureCount;
    private final int dataRaceCount;

    public VerifyResult(int failureCount, int dataRaceCount) {
        this.failureCount = failureCount;
        this.dataRaceCount = dataRaceCount;
    }

    public int failureCount() {
        return failureCount;
    }

    public int dataRaceCount() {
        return dataRaceCount;
    }
}
