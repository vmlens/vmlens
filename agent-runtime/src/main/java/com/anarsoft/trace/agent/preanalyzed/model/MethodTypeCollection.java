package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadJoin;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadStart;

import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_WRITE_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.LockMethod.*;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_READ_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.MethodWithLock.METHOD_WITH_WRITE_LOCK;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.NonBlockingMethod.*;

public class MethodTypeCollection {

    public MethodTypeCollection() {
    }

    public int id(MethodType methodType) {
        if(methodType instanceof ThreadStart) {
            return 0;
        }
        if(methodType instanceof ThreadJoin) {
            return 1;
        }

        // GetReadWriteLockMethod
        if(methodType == GET_READ_LOCK) {
            return 2;
        }
        if(methodType == GET_WRITE_LOCK) {
            return 3;
        }

        // LockMethod
        if(methodType == ENTER_REENTRANT_LOCK) {
            return 4;
        }
        if(methodType == EXIT_REENTRANT_LOCK) {
            return 5;
        }
        if(methodType == ENTER_READ_LOCK) {
            return 6;
        }
        if(methodType == EXIT_READ_LOCK) {
            return 7;
        }
        if(methodType == ENTER_WRITE_LOCK) {
            return 8;
        }
        if(methodType == EXIT_WRITE_LOCK) {
            return 9;
        }

        // MethodWithLock
        if(methodType == METHOD_WITH_READ_LOCK) {
            return 10;
        }
        if(methodType == METHOD_WITH_WRITE_LOCK) {
            return 11;
        }

        // NonBlockingMethod
        if(methodType == NON_BLOCKING_READ) {
            return 12;
        }
        if(methodType == NON_BLOCKING_WRITE) {
            return 13;
        }
        if(methodType == NON_BLOCKING_READ_WRITE) {
            return 14;
        }

        throw new RuntimeException("unknown " + methodType.getClass());
    }

    public MethodType type(int id) {
        if(id == 0) {
            return ThreadStart.SINGLETON;
        }
        if(id == 1) {
            return ThreadJoin.SINGLETON;
        }

        // GetReadWriteLockMethod
        if(id == 2) {
            return GET_READ_LOCK;
        }
        if(id == 3) {
            return GET_WRITE_LOCK;
        }

        // LockMethod
        if(id == 4) {
            return ENTER_REENTRANT_LOCK;
        }
        if(id == 5) {
            return EXIT_REENTRANT_LOCK;
        }
        if(id == 6) {
            return ENTER_READ_LOCK;
        }
        if(id == 7) {
            return EXIT_READ_LOCK;
        }
        if(id == 8) {
            return ENTER_WRITE_LOCK;
        }
        if(id == 9) {
            return EXIT_WRITE_LOCK;
        }

        // MethodWithLock
        if(id == 10) {
            return METHOD_WITH_READ_LOCK;
        }
        if(id == 11) {
            return METHOD_WITH_WRITE_LOCK;
        }

        // NonBlockingMethod
        if(id == 12) {
            return NON_BLOCKING_READ;
        }
        if(id == 13) {
            return NON_BLOCKING_WRITE;
        }
        if(id == 14) {
            return NON_BLOCKING_READ_WRITE;
        }

        throw new RuntimeException("unknown " + id);
    }

}
