package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

public interface ChoiceElement extends NodeBuilder {

    ChoiceElement getNext();
    void fill();
    void setLast(NodeBuilder last);

}
