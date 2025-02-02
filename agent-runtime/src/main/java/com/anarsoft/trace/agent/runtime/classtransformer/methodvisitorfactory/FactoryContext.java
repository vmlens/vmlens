package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

public class FactoryContext {

    private int methodId;
    private boolean useExpandedFrames;
    private boolean isStatic;
    private boolean isConstructor;
    private String className;
    private String description;

    public int methodId() {
        return methodId;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public boolean useExpandedFrames() {
        return useExpandedFrames;
    }

    public void setUseExpandedFrames(boolean useExpandedFrames) {
        this.useExpandedFrames = useExpandedFrames;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean aStatic) {
        isStatic = aStatic;
    }

    public boolean isConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean constructor) {
        isConstructor = constructor;
    }

    public String className() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String description() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
