package com.anarsoft.trace.agent.runtime.applyclasstransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.TransformerContext;

public interface TransformerStrategy {
    byte[] transform(TransformerContext context);
}
