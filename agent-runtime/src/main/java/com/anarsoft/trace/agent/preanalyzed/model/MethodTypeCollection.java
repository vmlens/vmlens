package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeAllStartWith;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeFilter;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.ClassTypeVmlensApi;
import com.anarsoft.trace.agent.preanalyzed.model.classtypeimpl.PreAnalyzedEqualNoOp;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodTypeThreadStart;

public class MethodTypeCollection {

    public MethodTypeCollection() {
    }

    public int id(MethodType classType) {
        if(classType instanceof MethodTypeThreadStart) {
            return 0;
        }
        throw new RuntimeException("unknown " + classType.getClass());
    }

    public MethodType type(int id) {
        if(id == 0) {
            return MethodTypeThreadStart.SINGLETON;
        }
        throw new RuntimeException("unknown " + id);
    }

}
