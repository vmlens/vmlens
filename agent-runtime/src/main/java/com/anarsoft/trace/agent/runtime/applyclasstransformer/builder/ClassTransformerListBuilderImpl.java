package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.*;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionPreAnalyzedFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ClassTransformerListBuilderImpl implements ClassTransformerListBuilder {

    private final TLinkedList<TLinkableWrapper<ClassFilterAndTransformerStrategy>> classArrayTransformerList =
            new TLinkedList<>();
    private final TransformerStrategyFactory transformerStrategyFactory;

    public ClassTransformerListBuilderImpl(TransformerStrategyFactory transformerStrategyFactory) {
        this.transformerStrategyFactory = transformerStrategyFactory;
    }

    private void add(ClassFilter classFilter, TransformerStrategy transformerStrategy) {
        classArrayTransformerList.add(wrap(new ClassFilterAndTransformerStrategy(classFilter, transformerStrategy)));
    }

    @Override
    public FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedSpecial() {
        return transformerStrategyFactory.createMethodBuilder();
    }

    @Override
    public FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedAtomicNonBlocking() {
        return null;
    }

    @Override
    public FactoryCollectionPreAnalyzedFactoryBuilder createPreAnalyzedAtomicReadWriteLock() {
        return null;
    }

    @Override
    public void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactory factoryCollectionPreAnalyzedBuilder) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.createPreAnalyzed(factoryCollectionPreAnalyzedBuilder));
    }

    @Override
    public void addVmlensApi(String name) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.createVmlensApi());
    }

    @Override
    public void addTraceStartsWith(String name) {
        add(new ClassFilterStartsWith(name), transformerStrategyFactory.createAll());
    }

    @Override
    public void addFilterStartsWith(String name) {
        add(new ClassFilterStartsWith(name), transformerStrategyFactory.createNoOp());
    }

    public ClassFilterAndTransformerStrategyCollection build() {
        return new ClassFilterAndTransformerStrategyCollection(classArrayTransformerList);
    }
}
