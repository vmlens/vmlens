package com.vmlens.trace.agent.bootstrap.stack;

public interface CallStack<ELEMENT> extends Stack<ELEMENT> {

    ELEMENT backward(int position);

    void remove(int count);

    void dup();

}
