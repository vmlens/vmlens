package com.anarsoft.trace.agent.runtime.classanalyzer;

import static org.objectweb.asm.Opcodes.ACC_SYNCHRONIZED;

public class AnalyzeMethodAccess {
    private final OnMethodAccess onMethodAccess;

    public AnalyzeMethodAccess(OnMethodAccess onMethodAccess) {
        this.onMethodAccess = onMethodAccess;
    }

    public void analyze(int access) {
        if ((access & ACC_SYNCHRONIZED) == ACC_SYNCHRONIZED) {
            onMethodAccess.onSynchronized();
        } else {
            onMethodAccess.onNotSynchronized();
        }
    }
}
