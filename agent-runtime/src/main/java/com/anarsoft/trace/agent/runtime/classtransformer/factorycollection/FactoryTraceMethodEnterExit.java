package com.anarsoft.trace.agent.runtime.classtransformer.factorycollection;

import com.anarsoft.trace.agent.runtime.classtransformer.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitor.AddMethodCall;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.MethodEnterExitAnalyzeFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitormethodenterexit.TryCatchBlockCounter;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryTraceMethodEnterExit {

    private final THashMap<NameAndDescriptor, TryCatchBlockCounter> nameAndDescriptorToTryCatchBlockCounter =
            new THashMap<>();
    private final MethodCallbackFactoryFactory factoryFactory;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryTraceMethodEnterExit(MethodCallbackFactoryFactory factoryFactory,
                                       MethodRepositoryForTransform methodCallIdMap) {
        this.factoryFactory = factoryFactory;
        this.methodCallIdMap = methodCallIdMap;
    }

    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor) {
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        TryCatchBlockCounter tryCatchBlockCounter = new TryCatchBlockCounter();
        MethodEnterExitAnalyzeFactory methodEnterExitAnalyzeFactory = new MethodEnterExitAnalyzeFactory(tryCatchBlockCounter);
        nameAndDescriptorToTryCatchBlockCounter.put(nameAndDescriptor, tryCatchBlockCounter);
        result.add(wrap(methodEnterExitAnalyzeFactory));
        return result;
    }

    public void addToTransform(NameAndDescriptor nameAndDescriptor,
                               TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result) {
        TryCatchBlockCounter tryCatchBlockCounter = nameAndDescriptorToTryCatchBlockCounter.get(nameAndDescriptor);
        result.add(wrap(new MethodEnterExitTransformFactory(tryCatchBlockCounter.tryCatchBlockCount(), factoryFactory)));
    }

    /**
     *
     * must be added last, since it applies to method calls
     *
     * @param result
     */
    public void addMethodCall(TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result) {
        result.add(wrap(AddMethodCall.factory(methodCallIdMap, factoryFactory)));
    }

}
