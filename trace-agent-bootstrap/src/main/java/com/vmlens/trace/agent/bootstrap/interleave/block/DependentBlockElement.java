package com.vmlens.trace.agent.bootstrap.interleave.block;

public interface DependentBlockElement {
    boolean startsAlternatingOrder(DependentBlockElement interleaveAction);
}
