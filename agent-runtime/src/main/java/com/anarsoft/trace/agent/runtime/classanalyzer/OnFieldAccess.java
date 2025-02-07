package com.anarsoft.trace.agent.runtime.classanalyzer;

import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForAnalyze;

public class OnFieldAccess {

    private final FieldRepositoryForAnalyze fieldRepositoryForAnalyze;
    private final FieldOwnerAndName fieldOwnerAndName;
    private int id;

    public OnFieldAccess(FieldRepositoryForAnalyze fieldRepositoryForAnalyze,
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
