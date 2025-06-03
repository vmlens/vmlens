package com.vmlens.trace.agent.bootstrap.preanalyzed.model;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ThreadStart;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ArrayNonBlockingMethod.*;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_WRITE_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.LockMethod.*;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_WRITE_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.NonBlockingMethod.*;

public class MethodTypeCollection {

    private final THashMap<AbstractMethodType,Integer> methodTypeToId;
    private final TIntObjectHashMap<AbstractMethodType> idToMethodType;

    MethodTypeCollection(THashMap<AbstractMethodType, Integer> methodTypeToId,
                                TIntObjectHashMap<AbstractMethodType> idToMethodType) {
        this.methodTypeToId = methodTypeToId;
        this.idToMethodType = idToMethodType;
    }


    public int id(MethodType methodType) {
        if(! methodTypeToId.containsKey(methodType)) {
            throw new RuntimeException("unknown " + methodType.getClass());
        }
        return methodTypeToId.get(methodType);

    }

    public MethodType type(int id) {
       if(! idToMethodType.containsKey(id)) {
           throw new RuntimeException("unknown " + id);
       }

        return idToMethodType.get(id);
    }

}
