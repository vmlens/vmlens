package com.vmlens.report.description;

import com.vmlens.report.element.LoopRunAndThreadIndex;

public interface DescriptionContext {

    String threadName(LoopRunAndThreadIndex key);

    String methodName(Integer key);

    String fieldName(Integer key);

    String loopName(Integer key);


}
