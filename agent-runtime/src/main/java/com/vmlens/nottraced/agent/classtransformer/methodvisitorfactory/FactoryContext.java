package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.MethodEnterExitStrategy;

public class FactoryContext {

    private int methodId;
    private String className;
    private MethodEnterExitStrategy methodEnterExitStrategy;
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

    public MethodEnterExitStrategy methodEnterExitStrategy() {
        return methodEnterExitStrategy;
    }

    public void setMethodEnterExitStrategy(MethodEnterExitStrategy methodEnterExitStrategy) {
        this.methodEnterExitStrategy = methodEnterExitStrategy;
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
