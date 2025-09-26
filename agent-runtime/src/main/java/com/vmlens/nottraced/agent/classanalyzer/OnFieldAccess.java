package com.vmlens.nottraced.agent.classanalyzer;

import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.transformed.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;

public class OnFieldAccess {

    private final FieldRepositoryForTransform fieldRepositoryForAnalyze;
    private final FieldOwnerAndName fieldOwnerAndName;
    private int id;

    public OnFieldAccess(FieldRepositoryForTransform fieldRepositoryForAnalyze,
                         FieldOwnerAndName fieldOwnerAndName) {
        this.fieldRepositoryForAnalyze = fieldRepositoryForAnalyze;
        this.fieldOwnerAndName = fieldOwnerAndName;
    }

    public void onNormal() {
        id = fieldRepositoryForAnalyze.getIdAndSetFieldIsNormal(fieldOwnerAndName);
    }

    public void onVolatile() {
        id = fieldRepositoryForAnalyze.getIdAndSetFieldIsVolatile(fieldOwnerAndName);
    }

    public void onFinal() {
        id = fieldRepositoryForAnalyze.getIdAndSetFieldIsFinal(fieldOwnerAndName);
    }

    public int id() {
        return id;
    }
}
