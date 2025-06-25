package com.vmlens.trace.agent.bootstrap.eventtype;

public class BarrierTypeNotify implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeNotify();


    private BarrierTypeNotify() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return null;
    }
}
