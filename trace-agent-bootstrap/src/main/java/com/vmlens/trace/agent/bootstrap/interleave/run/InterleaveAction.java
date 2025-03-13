package com.vmlens.trace.agent.bootstrap.interleave.run;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;

public interface InterleaveAction extends BlockBuilderWithThreadIndex {

    void addToBlockingLockRelationBuilder(Position position,
                                          BlockingLockRelationBuilder builder);

}
