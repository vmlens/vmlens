package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;

public class CallAfterForRuntimeEvent {

    public RuntimeEvent after(RuntimeEvent runtimeEvent, ActualRun actualRun) {

        BuildInterleaveActionRuntimeEventVisitor interleaveActionBuilder = new BuildInterleaveActionRuntimeEventVisitor();
        runtimeEvent.accept(interleaveActionBuilder);
        InterleaveAction interleaveAction = interleaveActionBuilder.build();
        if (interleaveAction == null) {
            return runtimeEvent;
        }
        InterleaveInfo interleaveInfo = actualRun.after(interleaveAction);

        if (interleaveInfo == null) {
            return null;
        }

        runtimeEvent.setRunPosition(interleaveInfo.runPosition());
        return runtimeEvent;
    }

}
