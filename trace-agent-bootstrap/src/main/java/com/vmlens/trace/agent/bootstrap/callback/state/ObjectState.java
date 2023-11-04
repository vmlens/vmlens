package com.vmlens.trace.agent.bootstrap.callback.state;

import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;
import com.vmlens.trace.agent.bootstrap.callback.field.UpdateObjectState;

public interface ObjectState {
    void sendNonVolatile(long threadId, int slidingWindowId, int fieldId,
                         int methodId, int operation, UpdateObjectState updateObjectStateNew, CallbackStatePerThreadForParallelize callbackStatePerThread);
}
