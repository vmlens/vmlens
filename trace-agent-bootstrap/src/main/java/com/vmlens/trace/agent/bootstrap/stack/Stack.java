package com.vmlens.trace.agent.bootstrap.stack;

public interface Stack<ELEMENT> {

    ELEMENT pop();

    void push(ELEMENT element);


}
