package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public interface TransformerStrategy {

    byte[] transform(TransformerContext context);

}
