package com.anarsoft.trace.agent.runtime.classanalyzer;

import static org.objectweb.asm.Opcodes.ACC_FINAL;
import static org.objectweb.asm.Opcodes.ACC_VOLATILE;

public class AnalyzeFieldAccess {

    private final OnFieldAccess onFieldAccess;

    public AnalyzeFieldAccess(OnFieldAccess onFieldAccess) {
        this.onFieldAccess = onFieldAccess;
    }

    public void analyze(int access) {
        boolean isVolatile = (access & ACC_VOLATILE) == ACC_VOLATILE;
        boolean isFinal = (access & ACC_FINAL) == ACC_FINAL;

        if (isFinal) {
            onFieldAccess.onFinal();
        } else if (isVolatile) {
            onFieldAccess.onVolatile();
        } else {
            onFieldAccess.onNormal();
        }
    }
}
