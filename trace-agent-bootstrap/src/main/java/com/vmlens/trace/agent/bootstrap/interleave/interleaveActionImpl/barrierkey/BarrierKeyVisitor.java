package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

public interface BarrierKeyVisitor {
    void visit(FutureKey futureKey);
    void visit(PhaserKey phaserKey);
}
