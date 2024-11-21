package com.vmlens.trace.agent.bootstrap.strategy;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.volatileaccessrepository.VolatileAccessRepository;

public interface FieldStrategy {

    void onAccess(Object fromObject, int fieldId, int position, int inMethodId, int memoryAccessType,
                  VolatileAccessRepository volatileAccessRepository,
                  ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

    void onStaticAccess(int fieldId, int position, int inMethodId, int memoryAccessType,
                        VolatileAccessRepository volatileAccessRepository,
                        ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter);

}
