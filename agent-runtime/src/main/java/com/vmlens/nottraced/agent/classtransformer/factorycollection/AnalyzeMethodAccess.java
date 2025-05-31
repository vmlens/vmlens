package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import static org.objectweb.asm.Opcodes.ACC_SYNCHRONIZED;

public class AnalyzeMethodAccess {
    private final OnMethodAccess onMethodAccess;

    public AnalyzeMethodAccess(OnMethodAccess onMethodAccess) {
        this.onMethodAccess = onMethodAccess;
    }

    public void analyze(int access, boolean isPotentialThreadRun) {
        if ((access & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED) {
            if (isPotentialThreadRun) {
                onMethodAccess.onSynchronizedAndThreadRun();
            } else {
                onMethodAccess.onSynchronized();
            }
        } else {
            if (isPotentialThreadRun) {
                onMethodAccess.onThreadRun();
            } else {
                onMethodAccess.onNotSynchronized();
            }
        }
    }
}
