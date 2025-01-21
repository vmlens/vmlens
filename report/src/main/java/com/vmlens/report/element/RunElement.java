package com.vmlens.report.element;

import com.vmlens.report.operationtextfactory.OperationTextFactory;

public class RunElement {

    private final LoopRunAndThreadIndex loopRunAndThreadIndex;
    private final int runPosition;
    private final StacktraceLeaf stacktraceLeaf;
    private final OperationTextFactory operationTextFactory;

    private final int inMethodId;

    public RunElement(LoopRunAndThreadIndex loopRunAndThreadIndex, int runPosition, StacktraceLeaf stacktraceLeaf,
                      OperationTextFactory operationTextFactory, int inMethodId) {
        this.loopRunAndThreadIndex = loopRunAndThreadIndex;
        this.runPosition = runPosition;
        this.stacktraceLeaf = stacktraceLeaf;
        this.operationTextFactory = operationTextFactory;
        this.inMethodId = inMethodId;
    }

    public LoopRunAndThreadIndex loopRunAndThreadIndex() {
        return loopRunAndThreadIndex;
    }

    public int runPosition() {
        return runPosition;
    }

    public StacktraceLeaf stacktraceLeaf() {
        return stacktraceLeaf;
    }

    public OperationTextFactory operationTextFactory() {
        return operationTextFactory;
    }

    public int inMethodId() {
        return inMethodId;
    }
}
