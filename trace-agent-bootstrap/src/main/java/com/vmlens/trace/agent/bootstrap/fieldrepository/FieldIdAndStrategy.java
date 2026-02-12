package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.EitherVolatileOrNormalFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;

public class FieldIdAndStrategy {

    private final int id;
    private final FieldStrategy strategy;

    public FieldIdAndStrategy(int id, FieldStrategy strategy) {
        this.id = id;
        this.strategy = strategy;
    }

    public int getId() {
        return id;
    }

    public FieldStrategy getStrategy() {
        return strategy;
    }


    // is null for no op and final
    public EitherVolatileOrNormalFieldAccessEvent create(Object forObject) {
        return strategy.create(forObject,id);
    }
}
