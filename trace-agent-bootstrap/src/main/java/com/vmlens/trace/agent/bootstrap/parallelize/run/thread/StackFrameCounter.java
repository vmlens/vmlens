package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import java.util.function.Consumer;

public class StackFrameCounter implements Consumer<StackWalker.StackFrame> {

    private int count = 0;

    @Override
    public void accept(StackWalker.StackFrame stackFrame) {
        count++;
    }

    public int count() {
        return count;
    }
}
