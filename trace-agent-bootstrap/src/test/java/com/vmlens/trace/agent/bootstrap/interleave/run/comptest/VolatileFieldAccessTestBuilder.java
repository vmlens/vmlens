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
        return read(null);
    }

    public InterleaveActionTestFactory write() {
        return write(null);
    }

    public InterleaveActionTestFactory read(PositionTestBuilder positionTestBuilder) {
        return new VolatileFieldAccessFactory(fieldId, MemoryAccessType.IS_READ, positionTestBuilder);
    }

    public InterleaveActionTestFactory write(PositionTestBuilder positionTestBuilder) {
        return new VolatileFieldAccessFactory(fieldId, MemoryAccessType.IS_WRITE, positionTestBuilder);
    }

    private static class VolatileFieldAccessFactory implements InterleaveActionTestFactory {

        private final int fieldId;
        private final int operation;
        private final PositionTestBuilder positionTestBuilder;

        public VolatileFieldAccessFactory(int fieldId, int operation,
                                          PositionTestBuilder positionTestBuilder) {
            this.fieldId = fieldId;
            this.operation = operation;
            this.positionTestBuilder = positionTestBuilder;
        }

        @Override
        public InterleaveAction create(int threadIndex, int positionInThread) {
            if (positionTestBuilder != null) {
                positionTestBuilder.setThreadIndex(threadIndex);
                positionTestBuilder.setPositionInThread(positionInThread);
            }

            return new VolatileFieldAccess(threadIndex, fieldId, operation);
        }
    }

}
