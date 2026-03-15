package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CreateCalleeFactoryProvider;

public class FactoryContext {

    private int methodId;
    private String className;
    private CreateCalleeFactoryProvider createCalleeFactoryProvider;
    private boolean isConstructor;
    private boolean needsVisitFrames;
    private boolean isStatic;

    public int methodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public String className() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public CreateCalleeFactoryProvider methodEnterExitStrategy() {
        return createCalleeFactoryProvider;
    }

    public void setMethodEnterExitStrategy(CreateCalleeFactoryProvider createCalleeFactoryProvider) {
        this.createCalleeFactoryProvider = createCalleeFactoryProvider;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setIsConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
    }

    public boolean needsVisitFrames() {
        return needsVisitFrames;
    }

    public void setNeedsVisitFrames(boolean needsVisitFrames) {
        this.needsVisitFrames = needsVisitFrames;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }
}
