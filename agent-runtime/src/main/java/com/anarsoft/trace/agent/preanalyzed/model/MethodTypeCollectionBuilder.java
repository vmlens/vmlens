package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;

public class MethodTypeCollectionBuilder {

    public TypeCollection<MethodType> build() {
        TypeCollection<MethodType> result = new TypeCollection<>();
        result.add(MethodTypeThreadStart.SINGLETON);
        return result;
    }


}
