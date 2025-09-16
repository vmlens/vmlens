package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;

public interface AddToKeyToOperationCollection {
    void addToKeyToOperationCollection(Position myPosition,
                                       ActiveLockCollection mapContainingStack,
                                       KeyToOperationCollection result);
}
