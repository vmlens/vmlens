package com.vmlens.trace.agent.bootstrap.eventtype;

public class BarrierTypeWait implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeWait();


    private BarrierTypeWait() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return threadIndex;
    }
}
