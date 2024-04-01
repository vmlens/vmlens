package com.vmlens.trace.agent.bootstrap.callback.field;


import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelizeProvider;

/**
 * everything which is the same for volatile and non volatile fields belongs into this class
 * everything which depends on volatile/non volatile comes in to FieldAccess
 */
public class FieldAccessAdapter {


    private final FieldAccess fieldAccess;
    private final ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider;

    private FieldAccessAdapter(FieldAccess fieldAccess,
                               ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider) {
        this.fieldAccess = fieldAccess;
        this.threadLocalForParallelizeProvider = threadLocalForParallelizeProvider;
    }

    static FieldAccessAdapter createForVolatile(int operation,
                                                GetOrCreateObjectState getAndUpdateVolatileObjectState,
                                                ThreadLocalForParallelizeProvider threadLocalForParallelizeProvider) {
        return new FieldAccessAdapter(new FieldAccessVolatile(operation, getAndUpdateVolatileObjectState),
                threadLocalForParallelizeProvider);
    }


    void field_access_default(Object orig, int fieldId, int methodId) {

    }

    void field_access_static(int fieldId, int methodId) {

    }

    // Fixme we do we need the Long (offset)
    Long field_access_from_generated_method(Object orig, Long offset, int
            fieldId, int methodId) {
        if (orig == null) {
            return null;
        }
        ThreadLocalForParallelize threadLocal = threadLocalForParallelizeProvider.getThreadLocalForParallelize();
        ThreadLocalDataWhenInTest dataWhenInTest = threadLocal.startCallbackProcessing();
        if (dataWhenInTest != null) {
            try {
                fieldAccess.field_access_from_generated_method(orig, fieldId, methodId, dataWhenInTest);
            } finally {
                dataWhenInTest.stopCallbackProcessing();
            }
        }
        return null;
    }
}
