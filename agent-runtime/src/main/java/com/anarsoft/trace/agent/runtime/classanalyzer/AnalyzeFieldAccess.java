package com.anarsoft.trace.agent.runtime.classanalyzer;

import static org.objectweb.asm.Opcodes.ACC_STATIC;
import static org.objectweb.asm.Opcodes.ACC_VOLATILE;

public class AnalyzeFieldAccess {

    private final OnFieldAccess onFieldAccess;

    public AnalyzeFieldAccess(OnFieldAccess onFieldAccess) {
        this.onFieldAccess = onFieldAccess;
    }

    public void analyze(int access) {
        // ToDo final

        boolean isVolatile = (access & ACC_VOLATILE) == ACC_VOLATILE;
        boolean isStatic = (access & ACC_STATIC) == ACC_STATIC;

        if (isVolatile) {
            if (isStatic) {
                onFieldAccess.onVolatileStatic();
            } else {
                onFieldAccess.onVolatile();
            }
        } else {
            if (isStatic) {
                onFieldAccess.onStatic();
            } else {
                onFieldAccess.onNormal();
            }
        }

    }


}
