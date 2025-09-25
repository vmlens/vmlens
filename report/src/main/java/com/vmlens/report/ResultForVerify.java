package com.vmlens.report;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class ResultForVerify {
    
    private final Map<Integer,Integer>  loopIdToDataRaceCount = new HashMap<>();
    private final List<LoopToDataRaceCount> loopToDataRaceCountList = new LinkedList<>();

    private int failureCount;
    private int dataRaceCount;
    private boolean noTestsRun;
    
    public void setDataRaces(int loopId, int count) {
        loopIdToDataRaceCount.put(loopId,count);
        dataRaceCount += count;
    }

    public void setLoopName(int loopId, String loopName) {
        if(loopIdToDataRaceCount.containsKey(loopId)) {
           int count =  loopIdToDataRaceCount.get(loopId);
            loopToDataRaceCountList.add(new LoopToDataRaceCount(loopName,count));
        }
    }

    public void setFailure(int loopId) {
        failureCount++;
    }

    public int failureCount() {
        return failureCount;
    }

    public int dataRaceCount() {
        return dataRaceCount;
    }

    public boolean noTestsRun() {
        return noTestsRun;
    }

    public void setNoTestsRun(boolean noTestsRun) {
        this.noTestsRun = noTestsRun;
    }

    // for test
    public List<LoopToDataRaceCount> loopToDataRaceCountList() {
        return loopToDataRaceCountList;
    }
}
