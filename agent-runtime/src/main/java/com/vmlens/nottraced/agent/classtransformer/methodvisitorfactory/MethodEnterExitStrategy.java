package com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory;

public interface MethodEnterExitStrategy {

    void createMethodEnter();
    void createMethodExitCall();
    void createMethodExitWithObjectReturnCall();

}
