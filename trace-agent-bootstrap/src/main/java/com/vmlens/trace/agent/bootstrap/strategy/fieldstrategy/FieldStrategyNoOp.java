package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.EitherVolatileOrNormalFieldAccessEvent;

public class FieldStrategyNoOp implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject,
                         int fieldId,
                         int position,
                         int inMethodId,
                         int memoryAccessType,
                         InTestActionProcessor inTestActionProcessor) {

    }

    @Override
    public void onStaticAccess(int fieldId,
                               int position,
                               int inMethodId,
                               int memoryAccessType,
                               InTestActionProcessor inTestActionProcessor) {

    }

    @Override
    public EitherVolatileOrNormalFieldAccessEvent create(Object forObject) {
        return null;
    }
}
