package com.vmlens.trace.agent.bootstrap.fieldrepository;

public interface FieldRepositoryForAnalyze extends FieldOwnerAndNameToIntMap {

    int getIdAndSetFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsNormal(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsFinal(FieldOwnerAndName fieldOwnerAndName);

}
