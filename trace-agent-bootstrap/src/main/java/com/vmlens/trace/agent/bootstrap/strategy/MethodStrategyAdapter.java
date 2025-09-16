package com.vmlens.trace.agent.bootstrap.strategy;

public interface MethodStrategyAdapter {

    void methodEnter(MethodContext enterExitContext);
    void methodExit(MethodContext enterExitContext);

}
