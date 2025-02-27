package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;


public interface FieldStrategy {

    void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

    void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

}
