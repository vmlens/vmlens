package com.anarsoft.trace.agent.runtime.classanalyzer;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.objectweb.asm.Opcodes.*;

public class AnalyzeFieldAccessTest {

    @Test
    public void volatileField() {
        OnFieldAccess onFieldAccess = analyze(ACC_PRIVATE | ACC_VOLATILE);
        verify(onFieldAccess).onVolatile();
    }

    @Test
    public void staticVolatileField() {
        OnFieldAccess onFieldAccess = analyze(ACC_PUBLIC | ACC_STATIC | ACC_VOLATILE);
        verify(onFieldAccess).onVolatileStatic();
    }

    private OnFieldAccess analyze(int access) {
        OnFieldAccess onFieldAccess = mock(OnFieldAccess.class);
        AnalyzeFieldAccess analyzeFieldAccess = new AnalyzeFieldAccess(onFieldAccess);
        analyzeFieldAccess.analyze(access);
        return onFieldAccess;
    }
}
