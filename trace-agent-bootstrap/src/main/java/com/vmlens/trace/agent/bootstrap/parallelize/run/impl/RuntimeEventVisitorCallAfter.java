package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEventOnly;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEventVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;


public class RuntimeEventVisitorCallAfter implements RuntimeEventVisitor {

    private final ActualRun actualRun;
    private RuntimeEvent result;

    public RuntimeEventVisitorCallAfter(ActualRun actualRun) {
        this.actualRun = actualRun;
    }

    @Override
    public void visit(RuntimeEventOnly runtimeEventOnly) {
        runtimeEventOnly.setRunPosition(actualRun.positionInRun());
        result = runtimeEventOnly;
    }

    @Override
    public void visit(InterleaveActionFactory interleaveActionFactory) {
        InterleaveInfo interleaveInfo = actualRun.after(interleaveActionFactory.create());
        interleaveActionFactory.setRunPosition(interleaveInfo.runPosition());
        result = interleaveActionFactory;
    }

    public RuntimeEvent result() {
        return result;
    }
}
