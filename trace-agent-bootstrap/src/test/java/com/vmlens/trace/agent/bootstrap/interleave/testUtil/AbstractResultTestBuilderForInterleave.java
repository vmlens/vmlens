package com.vmlens.trace.agent.bootstrap.interleave.testUtil;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public abstract class AbstractResultTestBuilderForInterleave implements ResultTestBuilder {
    @Override
    public void volatileAccess(int fieldId, int operation, Position position) {
        add(new VolatileFieldAccess(fieldId, operation), position);
    }

    protected abstract void add(InterleaveAction interleaveAction, Position position);
}
