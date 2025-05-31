package com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.AnalyzeFactory;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.FactoryContext;
import org.objectweb.asm.MethodVisitor;

public class MethodEnterExitAnalyzeFactory implements AnalyzeFactory {

    private final TryCatchBlockCounter tryCatchBlockCounter;

    public MethodEnterExitAnalyzeFactory(TryCatchBlockCounter tryCatchBlockCounter) {
        this.tryCatchBlockCounter = tryCatchBlockCounter;
    }

    @Override
    public MethodVisitor create(FactoryContext factoryContext, MethodVisitor previous) {
        return new MethodEnterExitAnalyze(previous, tryCatchBlockCounter);
    }
}
