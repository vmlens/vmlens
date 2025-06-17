package com.vmlens.nottraced.agent.applyclasstransformer.builder;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.nottraced.agent.applyclasstransformer.*;
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
    public FactoryCollectionPreAnalyzedFactoryBuilder createTraceNoMethodCall() {
        return transformerStrategyFactory.createTraceNoMethodCall();
    }

    @Override
    public void addPreAnalyzedEquals(String name, FactoryCollectionPreAnalyzedFactoryBuilder factoryCollectionPreAnalyzedBuilder) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.create(
                ((FactoryCollectionPreAnalyzedFactoryBuilderImpl)factoryCollectionPreAnalyzedBuilder).build() ) );
    }

    @Override
    public void addThreadPool(String name,
                              FactoryCollectionPreAnalyzedFactoryBuilder factoryCollectionPreAnalyzedFactoryBuilder) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.create(
                ((FactoryCollectionPreAnalyzedFactoryBuilderImpl)factoryCollectionPreAnalyzedFactoryBuilder).buildForThreadPool()));
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

    public void addClassNotYetImplemented(String name) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.createNotYetImplemented());
    }

    @Override
    public void addDoNotTraceIn(String name) {
        add(new ClassFilterEquals(name), transformerStrategyFactory.createDoNotTraceIn());
    }

    public ClassFilterAndTransformerStrategyCollection build() {
        return new ClassFilterAndTransformerStrategyCollection(classArrayTransformerList);
    }
}
