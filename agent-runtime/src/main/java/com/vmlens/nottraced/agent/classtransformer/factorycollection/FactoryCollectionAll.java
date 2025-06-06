package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryAll;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddArrayAccessAccessCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddFieldAccessCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddMonitorCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class FactoryCollectionAll extends FactoryCollectionPreAnalyzedOrAll {

    private final FactoryTraceMethodEnterExit factoryForBoth;
    private final FieldRepositoryForTransform fieldIdMap;


    public FactoryCollectionAll(FieldRepositoryForTransform fieldIdMap, MethodRepositoryForTransform methodCallIdMap) {
        super(methodCallIdMap);
        this.fieldIdMap = fieldIdMap;
        this.factoryForBoth = new FactoryTraceMethodEnterExit(new MethodCallbackFactoryFactoryAll(), methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyzeAfterFilter(NameAndDescriptor nameAndDescriptor, int access) {
        return factoryForBoth.getAnalyze(nameAndDescriptor);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter(
            NameAndDescriptor nameAndDescriptor,
            int access,
            int methodId,
            MethodRepositoryForTransform methodRepositoryForTransform) {
        boolean isPotentialThreadRun = false;
        if ((!isStatic(access)) && nameAndDescriptor.name().equals("run") && nameAndDescriptor.descriptor().equals("()V")) {
            isPotentialThreadRun = true;
        }

        new AnalyzeMethodAccess(new OnMethodAccess(methodRepositoryForTransform, methodId)).analyze(access,
                isPotentialThreadRun);

        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        result.add(wrap(AddMonitorCall.factory()));
        result.add(wrap(AddFieldAccessCall.factory(fieldIdMap)));
        result.add(wrap(AddArrayAccessAccessCall.factory()));
        factoryForBoth.addToTransform(nameAndDescriptor, result);
        factoryForBoth.addMethodCall(result);
        return result;
    }

    private boolean isStatic(int access) {
        return (access & ACC_STATIC) == ACC_STATIC;
    }
}
