package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.runtime.applyclasstransformer.*;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.FactoryCollectionDoNotTrace;
import com.anarsoft.trace.agent.runtime.classtransformer.factorycollection.factory.FactoryCollectionPreAnalyzedFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
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
        add(new ClassFilterEquals(name), transformerStrategyFactory.createPreAnalyzed(
                ((FactoryCollectionPreAnalyzedFactoryBuilderImpl)factoryCollectionPreAnalyzedBuilder).build() ) );
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

    @Override
    public void addDoNotTraceIn() {
        add(new ClassFilterEquals("java/lang/ClassLoader"), transformerStrategyFactory.createDoNotTraceIn());
    }

    public ClassFilterAndTransformerStrategyCollection build() {
        return new ClassFilterAndTransformerStrategyCollection(classArrayTransformerList);
    }
}
