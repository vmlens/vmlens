package com.vmlens.nottraced.agent.classtransformer.factorycollection;

import com.vmlens.nottraced.agent.classtransformer.NameAndDescriptor;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodEnterStrategyWithIntParam;
import com.vmlens.nottraced.agent.classtransformer.methodvisitor.AddMethodCall;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.MethodVisitorFactory;
import com.vmlens.shaded.gnu.trove.list.linked.TLinkedList;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.transformed.agent.bootstrap.methodrepository.MethodRepositoryForTransform;
import com.vmlens.transformed.agent.bootstrap.strategy.strategypreanalyzed.StrategyPreAnalyzed;
import com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper;

import static com.vmlens.nottraced.agent.classtransformer.methodvisitormethodenterexit.MethodEnterExitTransformFactory.addEnterExitTransform;
import static com.vmlens.transformed.agent.bootstrap.util.TLinkableWrapper.wrap;

public class FactoryCollectionPreAnalyzed extends FactoryCollectionPreAnalyzedOrAll {

    private final THashMap<NameAndDescriptor, StrategyPreAnalyzed> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;
    private final MethodRepositoryForTransform methodCallIdMap;

    public FactoryCollectionPreAnalyzed(THashMap<NameAndDescriptor,
                                                StrategyPreAnalyzed> methodToStrategy,
                                        MethodNotFoundAction methodNotFoundAction,
                                        MethodRepositoryForTransform methodCallIdMap) {
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
        this.methodCallIdMap = methodCallIdMap;
    }

    @Override
    public TLinkedList<TLinkableWrapper<MethodVisitorFactory>> getTransformAndSetStrategyAfterFilter(NameAndDescriptor nameAndDescriptor,
                                                                                          int access,
                                                                                          int methodId,
                                                                                          MethodRepositoryForTransform methodRepositoryForTransform) {
        StrategyPreAnalyzed strategy = methodToStrategy.get(nameAndDescriptor);
        if (strategy != null) {
            methodRepositoryForTransform.setStrategyPreAnalyzed(methodId, strategy);
            TLinkedList<TLinkableWrapper<MethodVisitorFactory>> result = TLinkableWrapper.emptyList();
            MethodTransformerBuilderImpl builder = new MethodTransformerBuilderImpl();
            strategy.addToBuilder(builder);
            addEnterExitTransform(builder.build(),result);
            // Fixme MethodCallbackFactoryFactoryPreAnalyzed auftrennen
            result.add(wrap(AddMethodCall.factory(methodCallIdMap, new MethodCallbackFactoryFactoryPreAnalyzed(new MethodEnterStrategyWithIntParam()))));
            return result;
        }
        switch (methodNotFoundAction) {
            case NO_OP: {
                return TLinkableWrapper.emptyList();
            }
            case WARNING_AND_NOT_TRANSFORM: {
                // Fixme create warning when public method
                return TLinkableWrapper.emptyList();
            }
        }
        return TLinkableWrapper.emptyList();
    }
}
