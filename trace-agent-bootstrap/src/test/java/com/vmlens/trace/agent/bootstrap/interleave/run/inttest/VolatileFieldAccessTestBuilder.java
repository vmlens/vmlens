package com.vmlens.trace.agent.bootstrap.interleave.run.inttest;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileAccess;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.volatileaccesskey.VolatileFieldAccessKey;
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
            return new VolatileAccess(threadIndex, new VolatileFieldAccessKey(fieldId,6L), operation);
        }
    }

}
