package com.vmlens.nottraced.agent.applyclasstransformer;

public interface TransformerStrategy {

    byte[] transform(TransformerContext context);

}
