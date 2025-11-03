package com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder;

public interface MethodTransformerBuilder {

    void withoutParam();
    void withIntParam();
    void withoutParamAndWithObjectReturn();

}
