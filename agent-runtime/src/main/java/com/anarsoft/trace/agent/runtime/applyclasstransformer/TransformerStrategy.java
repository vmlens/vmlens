package com.anarsoft.trace.agent.runtime.applyclasstransformer;

// To class
public interface TransformerStrategy {

    byte[] transform(TransformerContext context);

}
