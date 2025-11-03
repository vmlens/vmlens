package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithIntParam;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithoutParam;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;

public class MethodTransformerBuilderImpl implements MethodTransformerBuilder {

    private MethodCallbackFactoryFactory methodCallbackFactoryFactory;

    @Override
    public void withoutParam() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam());
    }

    @Override
    public void withIntParam() {
        methodCallbackFactoryFactory =
        new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithIntParam());

    }

    // Fixme special callback
    @Override
    public void withoutParamAndWithObjectReturn() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam());
    }

    public MethodCallbackFactoryFactory build() {
        return methodCallbackFactoryFactory;
    }
}
