package com.anarsoft.trace.agent.runtime.classarraytransformer;

public interface TransformerStrategy {
    byte[] transform(TransformerContext context);
}
