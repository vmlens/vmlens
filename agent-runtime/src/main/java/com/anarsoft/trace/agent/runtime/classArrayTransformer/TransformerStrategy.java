package com.anarsoft.trace.agent.runtime.classArrayTransformer;

public interface TransformerStrategy {
    byte[] transform(TransformerContext context);
}
