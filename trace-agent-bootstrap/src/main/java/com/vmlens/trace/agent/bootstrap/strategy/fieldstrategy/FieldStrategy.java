package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;


public interface FieldStrategy {

    void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                  InTestActionProcessor inTestActionProcessor);

    void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                        InTestActionProcessor inTestActionProcessor);

}
