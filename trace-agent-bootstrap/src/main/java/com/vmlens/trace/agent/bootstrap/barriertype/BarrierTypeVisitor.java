package com.vmlens.trace.agent.bootstrap.barriertype;

public interface BarrierTypeVisitor {
    
    void visit(BarrierTypeNotify barrierTypeNotify);
    void visit(BarrierTypeWait barrierTypeWait);
    void visit(BarrierTypeWaitNotify barrierTypeWaitNotify);
}
