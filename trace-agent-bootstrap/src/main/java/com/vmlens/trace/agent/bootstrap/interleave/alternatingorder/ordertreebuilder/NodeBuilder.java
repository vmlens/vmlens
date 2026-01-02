package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.ListElement;

public interface NodeBuilder {

    NodeBuilder getNext();
    ListElement build();

}
