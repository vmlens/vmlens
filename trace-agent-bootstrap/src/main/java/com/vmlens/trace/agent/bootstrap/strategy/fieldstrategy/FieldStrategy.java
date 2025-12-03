package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.EitherVolatileOrNormalFieldAccessEvent;


public interface FieldStrategy {

    void onAccess(Object fromObject,
                  int fieldId,
                  int position,
                  int inMethodId,
                  int memoryAccessType,
                  InTestActionProcessor inTestActionProcessor);

    void onStaticAccess(int fieldId,
                        int position,
                        int inMethodId,
                        int memoryAccessType,
                        InTestActionProcessor inTestActionProcessor);

    // is null for no op and final
    EitherVolatileOrNormalFieldAccessEvent create(Object forObject);

}
