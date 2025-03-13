package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;

interface Strategy {

    void addToBuilder(DependentBlock myBlock,
                      DependentBlock blockFromOtherThread,
                      OrderArraysBuilder orderArraysBuilder);

}
