package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface InterleaveActionFactory {

    InterleaveAction createAndSend(CreateInterleaveActionContext context,
                                   ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                                   SendEvent sendEvent,
                                   int positionInRun);
    boolean startsNewThread();

}
