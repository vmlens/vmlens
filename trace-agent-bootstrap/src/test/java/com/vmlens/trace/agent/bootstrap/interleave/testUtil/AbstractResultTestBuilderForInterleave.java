package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorEnterImpl;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.LockOrMonitorExit;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.lockOrMonitor.Monitor;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public abstract class AbstractResultTestBuilderForInterleave implements ResultTestBuilder {
    @Override
    public void volatileAccess(int fieldId, int operation, Position position) {
        add(new VolatileFieldAccess(fieldId, operation), position);
    }

    @Override
    public void monitorEnter(int id, Position temp) {
        add(new LockOrMonitorEnterImpl(new Monitor(id)), temp);
    }

    @Override
    public void monitorExit(int id, Position temp) {
        add(new LockOrMonitorExit(new Monitor(id)), temp);
    }

    protected abstract void add(InterleaveAction interleaveAction, Position position);
}
