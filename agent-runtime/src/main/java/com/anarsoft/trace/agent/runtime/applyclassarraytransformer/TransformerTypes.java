package com.anarsoft.trace.agent.runtime.applyclassarraytransformer;

public class TransformerTypes {

    public TransformerStrategy normal() {
        return new TransformerStrategyNormal();
    }

    public TransformerStrategy thread() {
        return new TransformerStrategyThread();
    }

    public TransformerStrategy vmlensApi() {
        return new TransformerStrategyVMLensApi();
    }
}
