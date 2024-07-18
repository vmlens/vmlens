package com.vmlens.trace.agent.bootstrap.interleave.run.comptest;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class VolatileFieldAccessTestBuilder {

    private final int fieldId;

    public VolatileFieldAccessTestBuilder(int fieldId) {
        this.fieldId = fieldId;
    }

    public static VolatileFieldAccessTestBuilder firstVolatileField() {
        return new VolatileFieldAccessTestBuilder(1);
    }

    public InterleaveActionTestFactory read() {
        return new VolatileFieldAccessFactory(fieldId, MemoryAccessType.IS_READ);
    }

    public InterleaveActionTestFactory write() {
        return new VolatileFieldAccessFactory(fieldId, MemoryAccessType.IS_WRITE);
    }

    private static class VolatileFieldAccessFactory implements InterleaveActionTestFactory {

        private final int fieldId;
        private final int operation;

        public VolatileFieldAccessFactory(int fieldId, int operation) {
            this.fieldId = fieldId;
            this.operation = operation;
        }

        @Override
        public InterleaveAction create(int threadIndex) {
            return new VolatileFieldAccess(threadIndex, fieldId, operation);
        }
    }

}
