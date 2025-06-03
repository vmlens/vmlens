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

public class MethodTypeCollectionFactory {

    private final THashMap<AbstractMethodType,Integer> methodTypeToId = new THashMap<>();
    private final TIntObjectHashMap<AbstractMethodType> idToMethodType = new TIntObjectHashMap<>();

    private int index = 0;


    public MethodTypeCollection create() {

        add(ThreadStart.SINGLETON);
        add(ThreadJoin.SINGLETON);

        add(GET_READ_WRITE_LOCK);

        add(ENTER_REENTRANT_LOCK);
        add(EXIT_REENTRANT_LOCK);

        add(ENTER_READ_LOCK);
        add(EXIT_READ_LOCK);

        add(ENTER_WRITE_LOCK);
        add(EXIT_WRITE_LOCK);

        add(METHOD_WITH_READ_LOCK);
        add(METHOD_WITH_WRITE_LOCK);

        add(NON_BLOCKING_READ);
        add(NON_BLOCKING_WRITE);
        add(NON_BLOCKING_READ_WRITE);

        add(ARRAY_NON_BLOCKING_READ);
        add(ARRAY_NON_BLOCKING_WRITE);
        add(ARRAY_NON_BLOCKING_READ_WRITE);

        add(NotYetImplementedMethod.SINGLETON);

        return new MethodTypeCollection(methodTypeToId,idToMethodType);
    }


    private void add(AbstractMethodType methodType) {
        int id = index;
        methodTypeToId.put(methodType,id);
        idToMethodType.put(id,methodType);
        index++;
    }

}
