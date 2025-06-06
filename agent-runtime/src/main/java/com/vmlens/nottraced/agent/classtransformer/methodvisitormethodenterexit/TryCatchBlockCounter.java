package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

public class TryCatchBlockCounter {

    private int tryCatchBlockCount = 0;

    public void incrementTryCatchBlockCount() {
        tryCatchBlockCount++;
    }

    public int tryCatchBlockCount() {
        return tryCatchBlockCount;
    }
}
