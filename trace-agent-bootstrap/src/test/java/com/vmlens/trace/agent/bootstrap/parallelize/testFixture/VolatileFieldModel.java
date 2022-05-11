package com.vmlens.trace.agent.bootstrap.parallelize.testFixture;

public class VolatileFieldModel implements SyncActionModel {

    public final int fieldId;
    public final int operation;

    public VolatileFieldModel(int fieldId, int operation) {
        this.fieldId = fieldId;
        this.operation = operation;
    }

    @Override
    public void accept(GivenRunVisitor visitor) {
        visitor.visit(this);
    }
}
