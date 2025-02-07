package com.vmlens.trace.agent.bootstrap.fieldidtostrategy;

import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;

public interface FieldIdToStrategy {

    FieldStrategy get(int fieldId);

}
