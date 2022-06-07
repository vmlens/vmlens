package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;


import com.vmlens.trace.agent.bootstrap.interleave.Position;

public final class OrderElementFactoryAndPosition<T> {

    public final Position position;
    public final boolean moreThanOneThread;
    public final T orderElementFactory;

    public OrderElementFactoryAndPosition(boolean moreThanOneThread, Position position, T orderElementFactory) {
        this.moreThanOneThread = moreThanOneThread;
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
