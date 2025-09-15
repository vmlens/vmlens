package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface PluginEventOnly extends RuntimeEvent {

    @Override
    default  void after(InterleaveRun interleaveRun,
                        CreateInterleaveActionContext context,
                        ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                        SendEvent sendEvent) {
        InterleaveInfo info = interleaveRun.actualRun().currentInterleaveInfo();
        if(info != null) {
            setRunPosition(info.runPosition());
            setMethodCounter(threadLocalWhenInTestForParallelize);
            sendEvent.sendSerializable(this);
        }
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return false;
    }

}
