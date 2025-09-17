package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface InterleaveActionFactoryAndRuntimeEvent extends RuntimeEvent , InterleaveActionFactory {

    InterleaveAction create(CreateInterleaveActionContext context);


    @Override
    default InterleaveAction createAndSend(CreateInterleaveActionContext context,
                                           ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                                           SendEvent sendEvent,
                                           int positionInRun) {
        this.setRunPosition(positionInRun);
        this.setMethodCounter(threadLocalWhenInTestForParallelize);
        sendEvent.sendSerializable(this);
        return create(context);
    }

    @Override
    default InterleaveActionFactory asInterleaveActionFactory() {
        return this;
    }

    @Override
    default PluginEventOnly asPluginEventOnly() {
        return null;
    }
}
