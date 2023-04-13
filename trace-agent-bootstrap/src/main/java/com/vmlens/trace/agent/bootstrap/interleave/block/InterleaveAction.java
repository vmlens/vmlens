package com.vmlens.trace.agent.bootstrap.interleave.block;

public interface InterleaveAction {
    BlockBuilderKey blockBuilderKey();
    BlockKey blockKey();
    boolean startsAlternatingOrder(InterleaveAction interleaveAction);
    boolean startsFixedOrder(InterleaveAction otherAction,int otherThreadIndex);
}
