package com.vmlens.trace.agent.bootstrap.interleave.run;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;

public interface InterleaveAction extends BlockBuilderWithThreadIndex {

    // Fixme add to KeyToOperationCollection

    void addToBlockingLockRelationBuilder(Position position,
                                          BlockingLockRelationBuilder builder);


    boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other);

}
