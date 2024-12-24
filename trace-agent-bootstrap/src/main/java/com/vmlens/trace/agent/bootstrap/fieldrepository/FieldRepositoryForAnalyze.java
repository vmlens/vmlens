package com.vmlens.trace.agent.bootstrap.fieldrepository;

public interface FieldRepositoryForAnalyze extends FieldOwnerAndNameToIntMap {

    int getIdAndSetFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsVolatileStatic(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsNormal(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsStatic(FieldOwnerAndName fieldOwnerAndName);

}
