package com.anarsoft.trace.agent.runtime.repository;

import com.vmlens.trace.agent.bootstrap.FieldIdAndTyp;
import com.vmlens.trace.agent.bootstrap.FieldIdRepository;
import com.vmlens.trace.agent.bootstrap.FieldName;
import com.vmlens.trace.agent.bootstrap.FieldTyp;
import com.vmlens.trace.agent.bootstrap.UndefinedFieldRepository;

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
