package com.vmlens.report;

public class LoopToDataRaceCount {


    private final String loopName;
    private final int dataRaceCount;

    public LoopToDataRaceCount(String loopName, int dataRaceCount) {
        this.loopName = loopName;
        this.dataRaceCount = dataRaceCount;
    }

    public String loopName() {
        return loopName;
    }

    public int dataRaceCount() {
        return dataRaceCount;
    }
}
