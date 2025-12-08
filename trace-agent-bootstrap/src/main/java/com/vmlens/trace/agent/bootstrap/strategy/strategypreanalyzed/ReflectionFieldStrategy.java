package com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.ExecuteRunAfter;
import com.vmlens.trace.agent.bootstrap.callback.intestaction.state.SetExecuteAfterOperation;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.EitherVolatileOrNormalFieldAccessEvent;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldOwnerAndName;
import com.vmlens.trace.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;
import com.vmlens.trace.agent.bootstrap.strategy.EnterExitContext;
import com.vmlens.trace.agent.bootstrap.strategy.fieldstrategy.FieldStrategy;

import java.lang.reflect.Field;

/*
 *  example get(T obj)
 *  so at the return the set the first param as call parameter similar
 *  to AtomicFieldUpdaterStrategy
 *  But then we cast this to Field to get the name of the field and
 *  look up the fieldId in FieldRepositoryForCallback
 *
 */
public class ReflectionFieldStrategy  implements StrategyPreAnalyzed{
    
    public static StrategyPreAnalyzed REFLECT_FIELD_READ = new ReflectionFieldStrategy(MemoryAccessType.IS_READ);
    public static StrategyPreAnalyzed REFLECT_FIELD_WRITE = new ReflectionFieldStrategy(MemoryAccessType.IS_WRITE);

    private final int operation;

    public ReflectionFieldStrategy(int operation) {
        this.operation = operation;
    }

    @Override
    public void methodEnter(EnterExitContext context) {
        // Noting to do
    }

    @Override
    public void methodExit(EnterExitContext context) {
        Field field = (Field) context.object();
        String className = field.getDeclaringClass().getName().replace('.','/');
        String fieldName = field.getName();
        FieldOwnerAndName fieldOwnerAndName = new FieldOwnerAndName(className,fieldName);
        FieldStrategy strategy = context.fieldUpdaterRepository().get(fieldOwnerAndName);
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
