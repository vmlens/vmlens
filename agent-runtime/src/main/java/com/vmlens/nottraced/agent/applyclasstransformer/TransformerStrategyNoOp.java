package com.vmlens.nottraced.agent.applyclasstransformer;

public class TransformerStrategyNoOp implements TransformerStrategy {
    @Override
    public byte[] transform(TransformerContext context) {
        return null;
    }
}
