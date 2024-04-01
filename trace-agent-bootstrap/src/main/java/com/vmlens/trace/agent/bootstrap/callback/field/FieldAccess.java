package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

/**
 * either volatile or non volatile
 */
public interface FieldAccess {
    void field_access_from_generated_method(Object orig, int
            fieldId, int methodId, ThreadLocalDataWhenInTest dataWhenInTest);
}
