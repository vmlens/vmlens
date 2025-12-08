package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.EitherVolatileOrNormalFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;

/*
 *  example get(T obj)
 *  so at the return the set the first param as call parameter
 *  Then we look up the fieldId in the FieldUpdaterRepository
 *
 */
public class AtomicFieldUpdaterStrategy implements StrategyPreAnalyzed {

    public static StrategyPreAnalyzed  ATOMIC_FIELD_READ = new AtomicFieldUpdaterStrategy(MemoryAccessType.IS_READ);
    public static StrategyPreAnalyzed  ATOMIC_FIELD_WRITE = new AtomicFieldUpdaterStrategy(MemoryAccessType.IS_WRITE);
    public static StrategyPreAnalyzed  ATOMIC_FIELD_READ_WRITE = new AtomicFieldUpdaterStrategy(MemoryAccessType.IS_READ_WRITE);


    private final int operation;

    public AtomicFieldUpdaterStrategy(int operation) {
        this.operation = operation;
    }

    @Override
    public void methodEnter(EnterExitContext context) {

    }

    @Override
    public void methodExit(EnterExitContext context) {
        FieldStrategy strategy = context.fieldUpdaterRepository().get(context.object());
        EitherVolatileOrNormalFieldAccessEvent event = strategy.create(context.objectParam());
        if(event != null) {
            event.setOperation(operation);
            event.setMethodId(context.methodId());
            ExecuteAfterOperation executeAfterOperation = new ExecuteRunAfter<>(event);
            context.inTestActionProcessor().process(new SetExecuteAfterOperation(executeAfterOperation));
        }
    }

    @Override
    public void addToBuilder(MethodTransformerBuilder methodTransformerBuilder) {
        methodTransformerBuilder.setWithObjectParamAtReturn();
    }
}
