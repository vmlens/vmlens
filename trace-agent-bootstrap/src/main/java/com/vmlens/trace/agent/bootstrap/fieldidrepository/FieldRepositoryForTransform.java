package com.vmlens.trace.agent.bootstrap.fieldidrepository;

public interface FieldRepositoryForTransform {

    int asInt(FieldOwnerAndName fieldId);
    int getIdAndSetFieldIsVolatile(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsNormal(FieldOwnerAndName fieldOwnerAndName);

    int getIdAndSetFieldIsFinal(FieldOwnerAndName fieldOwnerAndName);

}
