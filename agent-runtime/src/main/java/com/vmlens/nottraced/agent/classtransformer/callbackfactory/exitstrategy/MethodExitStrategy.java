package com.vmlens.nottraced.agent.classtransformer.callbackfactory.exitstrategy;

import com.vmlens.nottraced.agent.classtransformer.callbackfactory.MethodCallbackFactoryPreAnalyzed;
import com.vmlens.nottraced.agent.classtransformer.methodvisitorfactory.methodenterexitstrategy.CalleeFactory;

public interface MethodExitStrategy {

    /*
        this is for athrow see MethodEnterExitTransform
     */
    void methodExitWithObjectReturn(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory);
    void methodExit(MethodCallbackFactoryPreAnalyzed parent, int inMethodId, CalleeFactory calleeFactory);

}
