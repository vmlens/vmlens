package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;

// Fixme delete
public interface AddToKeyToOperationCollection {
    void blockBuilderAdd(Position myPosition, ActiveLockCollection mapContainingStack, MapOfBlocks result);
}
