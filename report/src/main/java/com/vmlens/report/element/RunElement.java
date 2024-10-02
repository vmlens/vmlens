package com.vmlens.report.element;

public class RunElement {

    private final LoopRunAndThreadIndex loopRunAndThreadIndex;
    private final String imagePath;
    private final StacktraceRoot stacktraceRoot;
    private final RunElementOperationTextFactory runElementOperationTextFactory;

    public RunElement(LoopRunAndThreadIndex loopRunAndThreadIndex, String imagePath, StacktraceRoot stacktraceRoot,
                      RunElementOperationTextFactory runElementOperationTextFactory) {
        this.loopRunAndThreadIndex = loopRunAndThreadIndex;
        this.imagePath = imagePath;
        this.stacktraceRoot = stacktraceRoot;
        this.runElementOperationTextFactory = runElementOperationTextFactory;
    }
}
