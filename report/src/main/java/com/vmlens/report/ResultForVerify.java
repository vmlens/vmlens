package com.vmlens.report;

public class ResultForVerify {

    private final int failureCount;
    private final int dataRaceCount;

    public ResultForVerify(int failureCount, int dataRaceCount) {
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
