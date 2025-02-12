package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.FactoryFactoryAll;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddFieldAccessCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddMonitorCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class FactoryCollectionAll implements FactoryCollection {

    private final FactoryForPreAnalyzedAndAll factoryForBoth;
    private final FieldRepositoryForTransform fieldIdMap;


    public FactoryCollectionAll(FieldRepositoryForTransform fieldIdMap, MethodRepositoryForTransform methodCallIdMap) {
        this.fieldIdMap = fieldIdMap;
        this.factoryForBoth = new FactoryForPreAnalyzedAndAll(new FactoryFactoryAll(), methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor) {
        return factoryForBoth.getAnalyze(nameAndDescriptor);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(
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
        factoryForBoth.addToTransform(nameAndDescriptor, result);
        return result;
    }


    private boolean isStatic(int access) {
        return (access & ACC_STATIC) == ACC_STATIC;
    }
}
