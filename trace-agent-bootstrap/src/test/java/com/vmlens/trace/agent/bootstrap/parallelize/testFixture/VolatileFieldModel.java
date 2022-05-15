package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

public class VolatileFieldModel implements SyncActionModel {

    public static VolatileFieldModel THREAD_ZERO_READ = new VolatileFieldModel(0, 1,
            MemoryAccessType.IS_READ);
    public static VolatileFieldModel THREAD_ONE_READ = new VolatileFieldModel(1, 1,
            MemoryAccessType.IS_READ);
    public static VolatileFieldModel THREAD_ONE_WRITE = new VolatileFieldModel(1, 1,
            MemoryAccessType.IS_WRITE);

    public final int threadIndex;
    public final int fieldId;
    public final int operation;

    public VolatileFieldModel(int threadIndex, int fieldId, int operation) {
        this.threadIndex = threadIndex;
        this.fieldId = fieldId;
        this.operation = operation;
    }

    @Override
    public void accept(SyncActionVisitor visitor) {
        visitor.visit(this);
    }
}
