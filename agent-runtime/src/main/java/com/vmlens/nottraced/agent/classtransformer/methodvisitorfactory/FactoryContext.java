package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory;

import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.MethodEnterExitStrategy;

public class FactoryContext {

    private int methodId;
    private String className;
    private MethodEnterExitStrategy methodEnterExitStrategy;
    private boolean isConstructor;

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
}
