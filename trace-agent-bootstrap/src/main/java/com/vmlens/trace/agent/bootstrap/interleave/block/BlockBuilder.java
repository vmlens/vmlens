package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;

public interface BlockBuilder extends WithThreadIndex, BlockElement {
    BlockBuilderKey key();
    void start(KeyToThreadIdToElementList<BlockKey,Block> result);
    void add(BlockBuilder next, KeyToThreadIdToElementList<BlockKey,Block> result);
}
