package com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;


public class FinalFieldStrategy implements FieldStrategy {
    @Override
    public void onAccess(Object fromObject, int fieldId, int position,
                         int inMethodId, int memoryAccessType,
                         ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }

    @Override
    public void onStaticAccess(int fieldId, int position, int inMethodId,
                               int memoryAccessType,
                               ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {

    }
}
