package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.block.OrderTreeBuilderWrapper;

interface Strategy {

    void addToBuilder(DependentBlock myBlock,
                      DependentBlock blockFromOtherThread,
                      OrderTreeBuilderWrapper orderArraysBuilder);

}
