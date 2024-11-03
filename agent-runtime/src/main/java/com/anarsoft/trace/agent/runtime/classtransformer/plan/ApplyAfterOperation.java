package com.anarsoft.trace.agent.runtime.classtransformer.plan;

import com.anarsoft.trace.agent.runtime.classtransformer.callbackfactory.MethodCallbackFactory;

public interface ApplyAfterOperation {

    void afterOperation(MethodCallbackFactory callbackCallFactory);

}
