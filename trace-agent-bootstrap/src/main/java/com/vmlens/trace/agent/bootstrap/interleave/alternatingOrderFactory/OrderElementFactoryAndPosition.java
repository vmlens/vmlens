package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;


import com.vmlens.trace.agent.bootstrap.interleave.Position;

public final class OrderElementFactoryAndPosition<T> {

    public final Position position;
    public final int activeThreadCount;
    public final T orderElementFactory;

    public OrderElementFactoryAndPosition(int activeThreadCount, Position position, T orderElementFactory) {
        this.activeThreadCount = activeThreadCount;
        this.position = position;
        this.orderElementFactory = orderElementFactory;
    }


    @Override
    public String toString() {
        return "SyncAction{" +
                "position=" + position +
                ", syncActionFromRun=" + orderElementFactory +
                '}';
    }
}
