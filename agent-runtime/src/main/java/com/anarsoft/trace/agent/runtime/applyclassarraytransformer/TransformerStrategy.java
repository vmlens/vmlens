package com.anarsoft.trace.agent.runtime.applyclassarraytransformer;

import com.anarsoft.trace.agent.runtime.classarraytransformer.TransformerContext;

public interface TransformerStrategy {
    byte[] transform(TransformerContext context);
}
