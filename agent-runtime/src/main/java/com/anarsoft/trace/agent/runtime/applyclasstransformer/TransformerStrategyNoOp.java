package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public class TransformerStrategyNoOp implements TransformerStrategy {
    @Override
    public byte[] transform(TransformerContext context) {
        return null;
    }
}
