package com.vmlens.trace.agent.bootstrap.callback.callbackaction.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackAction;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.InTestActionProcessor;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForCallback;

public class BeforeFieldAccessAction implements CallbackAction  {

    private final FieldRepositoryForCallback fieldIdToStrategy;
    private final Object fromObject;
    private final int fieldId;
    private final int position;
    private final int inMethodId;
    private final int operation;

    public BeforeFieldAccessAction(FieldRepositoryForCallback fieldIdToStrategy,
                                   Object fromObject,
                                   int fieldId,
                                   int position,
                                   int inMethodId,
                                   int operation) {
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.fromObject = fromObject;
        this.fieldId = fieldId;
        this.position = position;
        this.inMethodId = inMethodId;
        this.operation = operation;
    }

    @Override
    public void execute(InTestActionProcessor inTestActionProcessor) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                operation, inTestActionProcessor);
    }
}
