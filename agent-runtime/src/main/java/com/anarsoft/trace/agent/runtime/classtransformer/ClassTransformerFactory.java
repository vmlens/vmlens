package com.anarsoft.trace.agent.runtime.classtransformer;

import com.anarsoft.trace.agent.runtime.classtransformer.methodfilter.MethodFilter;

public interface ClassTransformerFactory {

    ClassTransformer create(MethodFilter methodFilter);

}
