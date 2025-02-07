package com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory;

import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.methodnotfoundaction.MethodNotFoundAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.strategy.preanalyzedstrategy.PreAnalyzedStrategy;

public class FactoryCollectionPreAnalyzedFactory {

    private final THashMap<NameAndDescriptor, PreAnalyzedStrategy> methodToStrategy;
    private final MethodNotFoundAction methodNotFoundAction;

    public FactoryCollectionPreAnalyzedFactory(THashMap<NameAndDescriptor, PreAnalyzedStrategy> methodToStrategy,
                                               MethodNotFoundAction methodNotFoundAction) {
        this.methodToStrategy = methodToStrategy;
        this.methodNotFoundAction = methodNotFoundAction;
    }
}
