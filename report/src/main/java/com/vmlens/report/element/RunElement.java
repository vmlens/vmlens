package com.vmlens.report.element;

import com.vmlens.report.runelementtype.RunElementType;

public class RunElement {

    private final LoopRunAndThreadIndex loopRunAndThreadIndex;
    private final int runPosition;
    private final StacktraceLeaf stacktraceLeaf;
    private final RunElementType operationTextFactory;

    private final int inMethodId;

    public RunElement(LoopRunAndThreadIndex loopRunAndThreadIndex, int runPosition, StacktraceLeaf stacktraceLeaf,
                      RunElementType operationTextFactory, int inMethodId) {
        this.loopRunAndThreadIndex = loopRunAndThreadIndex;
        this.runPosition = runPosition;
        this.stacktraceLeaf = stacktraceLeaf;
        this.operationTextFactory = operationTextFactory;
        this.inMethodId = inMethodId;
    }

    public int runId() {
        return loopRunAndThreadIndex.runId();
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

    public RunElementType operationTextFactory() {
        return operationTextFactory;
    }

    public int inMethodId() {
        return inMethodId;
    }
}
