package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

public class ThreadIdToState {
    public int threadIndexForThreadId(long id) {
        return (int) id;
    }
}
