package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey;

public interface BarrierKeyVisitor {
    void visit(FutureKey futureKey);
    void visit(PhaserKey phaserKey);
}
