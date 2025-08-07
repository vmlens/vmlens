package com.vmlens.trace.agent.bootstrap.preanalyzed.model.classtypeimpl;

import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.ClassTransformerListBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.PreAnalyzedMethod;

public abstract class AbstractPreAnalyzed extends AbstractClassType {

    @Override
    public void addToBuilder(String name,
                             PreAnalyzedMethod[] methods,
                             ClassTransformerListBuilder classBuilder) {
        FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder = create(classBuilder);
        for (PreAnalyzedMethod method : methods) {
            method.add(name,methodBuilder);
        }
        classBuilder.addPreAnalyzedEquals(name, methodBuilder);
    }

    protected abstract FactoryCollectionPreAnalyzedFactoryBuilder create(ClassTransformerListBuilder classBuilder);


}
