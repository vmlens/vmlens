package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;

public class ProcessAfter {

    public static void process(AfterContextForStateMachine afterContext,
                               SendEvent sendEvent,
                               RunStateContext runStateContext,
                               int startedThreadIndex) {
            afterContext.runtimeEvent().setStartedThreadIndex(startedThreadIndex);
            afterContext.runtimeEvent().after(runStateContext.interleaveRun(),
                    runStateContext.runContext(),
                    afterContext.threadLocalDataWhenInTest(),sendEvent);
    }
}
