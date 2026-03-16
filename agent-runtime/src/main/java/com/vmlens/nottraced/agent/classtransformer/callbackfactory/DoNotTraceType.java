package com.vmlens.nottraced.agent.classtransformer.callbackfactory;

public enum DoNotTraceType {

    EVERYWHERE("DoNotTraceCallback") ,
    IN_TEST("DoNotTraceInTestCallback");

    private final String callbackClassName;

    DoNotTraceType(String callbackClassName) {
        this.callbackClassName = callbackClassName;
    }

    public String callbackClassName() {
        return callbackClassName;
    }
}
