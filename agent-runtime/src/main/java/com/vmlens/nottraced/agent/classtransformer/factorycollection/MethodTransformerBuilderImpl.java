package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactory;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy.MethodEnterStrategyWithIntParam;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.enterstrategy.MethodEnterStrategyWithoutParam;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy.*;
import com.vmlens.transformed.agent.bootstrap.preanalyzed.methodtransformerbuilder.MethodTransformerBuilder;

public class MethodTransformerBuilderImpl implements MethodTransformerBuilder {

    private MethodCallbackFactoryFactory methodCallbackFactoryFactory;

    @Override
    public void setWithoutParam() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam(),
                        new DefaultMethodExitStrategy(),
                        true);
    }

    @Override
    public void setWithIntParam() {
        methodCallbackFactoryFactory =
        new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithIntParam(),
                new DefaultMethodExitStrategy(),
                true);

    }

    // Fixme special callback  ObjectReturnMethodExitStrategy
    @Override
    public void setWithoutParamAndWithObjectReturn() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam(),
                        new ObjectReturnMethodExitStrategy(),
                        true);
    }

    @Override
    public void setWithObjectParamAtReturn() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam(),
                        new ObjectParamMethodExitStrategy(),
                        true);
    }

    @Override
    public void setWithObjectStringParamAtReturn() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam(),
                        new ObjectStringParamObjectReturnMethodExitStrategy(),
                        true);
    }

    @Override
    public void setWithObjectPlaceHolderStringParamAtReturn() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithoutParam(),
                        new ObjectPlaceHolderStringParamObjectReturnExitStrategy(),
                        true);
    }

    @Override
    public void setWithoutParamForInitialize() {
        methodCallbackFactoryFactory =
                new MethodCallbackFactoryFactoryPreAnalyzed(null,
                        new ObjectReturnForInitializeMethodExitStrategy(),
                        false);
    }

    public MethodCallbackFactoryFactory build() {
        return methodCallbackFactoryFactory;
    }
}
