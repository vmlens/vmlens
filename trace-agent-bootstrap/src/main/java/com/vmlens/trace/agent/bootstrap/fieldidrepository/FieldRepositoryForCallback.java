package com.vmlens.trace.agent.bootstrap.fieldidrepository;

import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;

public interface FieldRepositoryForCallback {

    FieldStrategy get(int fieldId);

}
