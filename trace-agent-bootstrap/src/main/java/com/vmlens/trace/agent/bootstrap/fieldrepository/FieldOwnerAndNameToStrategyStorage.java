package com.vmlens.trace.agent.bootstrap.fieldrepository;

public interface FieldOwnerAndNameToStrategyStorage {

    void setFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName);

    void setFieldIsVolatileStatic(FieldOwnerAndName fieldOwnerAndName);

    void setFieldIsNormal(FieldOwnerAndName fieldOwnerAndName);

    void setFieldIsStatic(FieldOwnerAndName fieldOwnerAndName);

}
