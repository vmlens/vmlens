package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;


public interface AddToKeyToOperationCollection {
    void addToCollection(Position myPosition, ActiveLockCollection mapContainingStack, KeyToOperationCollection result);
}
