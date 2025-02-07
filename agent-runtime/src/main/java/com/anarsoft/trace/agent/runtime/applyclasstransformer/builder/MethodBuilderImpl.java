package com.anarsoft.trace.agent.runtime.applyclasstransformer.builder;

import com.anarsoft.trace.agent.preanalyzed.builder.MethodBuilder;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.FactoryCollectionPreAnalyzedFactory;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.NameAndDescriptor;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.methodnotfoundaction.MethodNotFoundAction;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.methodnotfoundaction.NoOpAction;
import com.anarsoft.trace.agent.runtime.classtransformer.methodvisitorfactory.methodnotfoundaction.WarningAction;
import com.vmlens.shaded.gnu.trove.map.hash.THashMap;
import com.vmlens.trace.agent.bootstrap.strategy.preanalyzedstrategy.PreAnalyzedStrategy;
import com.vmlens.trace.agent.bootstrap.strategy.preanalyzedstrategy.ThreadStartStrategy;

public class MethodBuilderImpl implements MethodBuilder {

    private final THashMap<NameAndDescriptor, PreAnalyzedStrategy> methodToStrategy = new
            THashMap<>();
    private MethodNotFoundAction methodNotFoundAction = new WarningAction();


    @Override
    public void addThreadStart(String name, String desc) {
        methodToStrategy.put(new NameAndDescriptor(name, desc), new ThreadStartStrategy());
    }

    @Override
    public void noOpWhenMethodNotFound() {
        methodNotFoundAction = new NoOpAction();
    }

    @Override
    public FactoryCollectionPreAnalyzedFactory build() {
        return new FactoryCollectionPreAnalyzedFactory(methodToStrategy, methodNotFoundAction);
    }
}
