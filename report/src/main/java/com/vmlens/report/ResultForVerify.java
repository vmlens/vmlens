package com.vmlens.report;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

public class ResultForVerify {

    // For Test
    private final Map<Integer,Integer>  loopIdToDataRaceCount = new HashMap<>();
    // For Test
    private final Map<Integer,String>   idToLoopName = new HashMap<>();

    private int failureCount;
    private int dataRaceCount;
    private boolean noTestsRun;
    private boolean noAgentRun;
    
    public void setDataRaces(int loopId, int count) {
        loopIdToDataRaceCount.put(loopId,count);
        dataRaceCount += count;
    }

    // For Test
    public void setLoopName(int loopId, String loopName) {
        idToLoopName.put(loopId,loopName);
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

    public boolean noAgentRun() {
        return noAgentRun;
    }

    public void setNoAgentRun(boolean noAgentRun) {
        this.noAgentRun = noAgentRun;
    }

    // for test
    public List<LoopToDataRaceCount> loopToDataRaceCountList() {
        List<LoopToDataRaceCount> result = new LinkedList<>();
        for(Map.Entry<Integer,Integer>  entry :  loopIdToDataRaceCount.entrySet()) {
            result.add(new LoopToDataRaceCount(idToLoopName.get(entry.getKey()) , entry.getValue()));
        }
        return result;
    }
}
