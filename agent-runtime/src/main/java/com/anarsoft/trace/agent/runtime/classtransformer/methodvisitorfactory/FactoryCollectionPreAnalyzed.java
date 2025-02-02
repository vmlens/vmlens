package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryCollectionPreAnalyzed {

    private final MapForList<AnalyzeFactoryFactory> analyzeFactoryFactoryMap;
    private final MapForList<TransformFactory> transformFactoryMap;

    public FactoryCollectionPreAnalyzed(MapForList<AnalyzeFactoryFactory> analyzeFactoryFactoryMap,
                                        MapForList<TransformFactory> transformFactoryMap) {
        this.analyzeFactoryFactoryMap = analyzeFactoryFactoryMap;
        this.transformFactoryMap = transformFactoryMap;
    }

    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getAnalyze(NameAndDescriptor nameAndDescriptor) {
        TLinkedList<TLinkableWrapper<AnalyzeFactoryFactory>> factoryFactoryList = analyzeFactoryFactoryMap.get(nameAndDescriptor);
        if (factoryFactoryList == null) {
            return TLinkableWrapper.emptyList();
        }
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        for (TLinkableWrapper<AnalyzeFactoryFactory> factoryFactory : factoryFactoryList) {
            AnalyzeAndTransformFactory analyzeAndTransformFactory = factoryFactory.element().create();
            result.add(wrap(analyzeAndTransformFactory.analyzeFactory()));
            transformFactoryMap.put(nameAndDescriptor, analyzeAndTransformFactory.transformFactory());
        }
        return result;
    }

    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategy(NameAndDescriptor nameAndDescriptor,
                                                                                          boolean isSynchronized) {
        TLinkedList<TLinkableWrapper<TransformFactory>> factoryList = transformFactoryMap.get(nameAndDescriptor);
        if (factoryList == null) {
            return TLinkableWrapper.emptyList();
        }
        TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = new TLinkedList<>();
        for (TLinkableWrapper<TransformFactory> factory : factoryList) {
            result.add(wrap(factory.element()));
        }
        return result;
    }
}
