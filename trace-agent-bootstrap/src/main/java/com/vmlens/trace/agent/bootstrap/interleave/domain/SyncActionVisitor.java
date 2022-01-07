package com.vmlens.trace.agent.bootstrap.interleave.domain;

import com.vmlens.trace.agent.bootstrap.interleave.domain.syncAction.VolatileFieldAccess;

public interface SyncActionVisitor {
    void visit(VolatileFieldAccess volatileFieldAccess);
}
