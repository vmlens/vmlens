package com.anarsoft.trace.agent.runtime.applyclasstransformer;

public class TransformerTypes {

    public TransformerStrategy normal() {
        return new TransformerStrategyNormal();
    }

    public TransformerStrategy java() {
        return new TransformerStrategyThread();
    }

    public TransformerStrategy vmlensApi() {
        return new TransformerStrategyVMLensApi();
    }
}
