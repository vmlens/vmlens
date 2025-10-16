package com.vmlens.trace.agent.bootstrap.interleave.buildcalculatedrun;

public class MaxAncCurrentCount {

    private int maxCount = 1;
    private int currentCount = 0;

    public void incrementMaxCount() {
        maxCount++;
    }

    public void incrementCurrentCount() {
        currentCount++;
    }

    public boolean checkCurrentCountAndReset() {
        if(currentCount > maxCount) {
            throw new RuntimeException("should not happen");
        }
        boolean maxCountReached = (maxCount == currentCount);
        currentCount=0;
        return maxCountReached;
    }

}
