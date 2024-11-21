package com.vmlens.trace.agent.bootstrap.fieldrepository;

import com.vmlens.trace.agent.bootstrap.strategy.FieldStrategy;

public interface FieldIdToStrategy {

    FieldStrategy get(int fieldId);

}
