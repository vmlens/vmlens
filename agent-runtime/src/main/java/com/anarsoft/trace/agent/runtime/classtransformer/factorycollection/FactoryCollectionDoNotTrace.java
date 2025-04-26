package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactoryFactoryDoNotTrace;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.set.hash.THashSet;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static org.objectweb.asm.Opcodes.ACC_PUBLIC;
import static org.objectweb.asm.Opcodes.ACC_STATIC;

public class FactoryCollectionDoNotTrace implements FactoryCollection {

    private final FactoryForPreAnalyzedAndAll factoryForBoth;


    public FactoryCollectionDoNotTrace(MethodRepositoryForTransform methodCallIdMap) {


        this.factoryForBoth = new FactoryForPreAnalyzedAndAll(new MethodCallbackFactoryFactoryDoNotTrace(),
                methodCallIdMap);
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor, int access) {
        if(doNotTraceInMethod(nameAndDescriptor,access)) {
            return factoryForBoth.getAnalyze(nameAndDescriptor);
        }
        return TLinkableWrapper.emptyList();
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
        if(doNotTraceInMethod(nameAndDescriptor,access)) {
            factoryForBoth.addToTransform(nameAndDescriptor, result);
        }
        return result;
    }

    private boolean doNotTraceInMethod(NameAndDescriptor nameAndDescriptor, int access) {
        if ((access & ACC_PUBLIC) != ACC_PUBLIC) {
                return false;
        }
       return nameAndDescriptor.name().equals("loadClass") &&
               nameAndDescriptor.descriptor().equals("(Ljava/lang/String;)Ljava/lang/Class;");
    }

}
