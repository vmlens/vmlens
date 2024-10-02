package com.vmlens.report.element;

public class RunElement {

    private final LoopRunAndThreadIndex loopRunAndThreadIndex;
    private final int runPosition;
    private final StacktraceLeaf stacktraceLeaf;
    private final OperationTextFactory operationTextFactory;

    public RunElement(LoopRunAndThreadIndex loopRunAndThreadIndex, int runPosition, StacktraceLeaf stacktraceLeaf,
                      OperationTextFactory operationTextFactory) {
        this.loopRunAndThreadIndex = loopRunAndThreadIndex;
        this.runPosition = runPosition;
        this.stacktraceLeaf = stacktraceLeaf;
        this.operationTextFactory = operationTextFactory;
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
}
