package com.anarsoft.trace.agent.runtime.repositorydeprecated;

import com.vmlens.trace.agent.bootstrap.*;

public class FieldRepositoryFacade {


    public static synchronized FieldIdAndTyp get(String owner, String name) {
        FieldIdAndTyp fieldIdAndTyp = FieldIdRepository.get(owner, name);
        if (fieldIdAndTyp != null) {
            return fieldIdAndTyp;
        }
        int unknownId = UndefinedFieldRepository.unknownIdArray.getOrCreateId(new FieldName(owner, name));
        return new FieldIdAndTyp(unknownId, FieldTyp.UNDEFINED);
    }

}
