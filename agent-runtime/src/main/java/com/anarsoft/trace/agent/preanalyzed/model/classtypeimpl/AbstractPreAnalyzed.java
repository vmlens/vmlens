package com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl;

import com.anarsoft.trace.agent.preanalyzed.builder.ClassTransformerListBuilder;
import com.anarsoft.trace.agent.preanalyzed.builder.FactoryCollectionPreAnalyzedFactoryBuilder;
import com.anarsoft.trace.agent.preanalyzed.model.PreAnalyzedMethod;

public abstract class AbstractPreAnalyzed extends AbstractClassType {

    @Override
    public void addToBuilder(String name,
                             PreAnalyzedMethod[] methods,
                             ClassTransformerListBuilder classBuilder) {
        FactoryCollectionPreAnalyzedFactoryBuilder methodBuilder = create(classBuilder);
        for (PreAnalyzedMethod method : methods) {
            method.add(methodBuilder);
        }
        classBuilder.addPreAnalyzedEquals(name, methodBuilder.build());
    }

    protected abstract FactoryCollectionPreAnalyzedFactoryBuilder create(ClassTransformerListBuilder classBuilder);


}
