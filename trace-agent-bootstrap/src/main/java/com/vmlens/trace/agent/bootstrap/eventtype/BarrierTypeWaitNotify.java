package com.vmlens.trace.agent.bootstrap.eventtype;

public class BarrierTypeWaitNotify implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeWaitNotify();


    private BarrierTypeWaitNotify() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return threadIndex;
    }

}
