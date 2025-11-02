package com.vmlens.trace.agent.bootstrap.preanalyzed.model;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.*;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.ArrayNonBlockingMethod.*;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_WRITE_LOCK;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.LockMethod.*;
import static com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.MethodToStrategy.*;
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
        add(NEW_CONDITION);

        add(METHOD_WITH_READ_LOCK);
        add(METHOD_WITH_WRITE_LOCK);

        add(NON_BLOCKING_READ);
        add(NON_BLOCKING_WRITE);
        add(NON_BLOCKING_READ_WRITE);

        add(ARRAY_NON_BLOCKING_READ);
        add(ARRAY_NON_BLOCKING_WRITE);
        add(ARRAY_NON_BLOCKING_READ_WRITE);

        add(NotYetImplementedMethod.SINGLETON);
        
        add(ThreadPoolStart.SINGLETON);
        add(ThreadPoolJoin.JOIN_ALL);

        add(CONDITION_AWAIT);

        add(FUTURE_GET);
        add(FUTURE_SET);
        add(METHOD_ENTER_EXIT);


        add(ENTER_STAMPED_READ_LOCK);
        add(ENTER_STAMPED_WRITE_LOCK);
        add(EXIT_STAMPED_LOCK);
        add(GET_LOCK_STATE);

        return new MethodTypeCollection(methodTypeToId,idToMethodType);
    }

    private void add(AbstractMethodType methodType) {
        int id = index;
        methodTypeToId.put(methodType,id);
        idToMethodType.put(id,methodType);
        index++;
    }

}
