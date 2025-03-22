package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadJoin;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadStart;

public class MethodTypeCollection {

    public MethodTypeCollection() {
    }

    public int id(MethodType classType) {
        if(classType instanceof ThreadStart) {
            return 0;
        }
        if(classType instanceof ThreadJoin) {
            return 1;
        }
        throw new RuntimeException("unknown " + classType.getClass());
    }

    public MethodType type(int id) {
        if(id == 0) {
            return ThreadStart.SINGLETON;
        }
        if(id == 1) {
            return ThreadJoin.SINGLETON;
        }
        throw new RuntimeException("unknown " + id);
    }

}
