package com.anarsoft.trace.agent.preanalyzed.model;

import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.NotYetImplementedMethod;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadJoin;
import com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ThreadStart;

import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.ArrayNonBlockingMethod.*;
import static com.anarsoft.trace.agent.preanalyzed.model.methodtypeimpl.GetReadWriteLockMethod.GET_READ_WRITE_LOCK;
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
        if(methodType == GET_READ_WRITE_LOCK) {
            return 2;
        }

        // LockMethod
        if(methodType == ENTER_REENTRANT_LOCK) {
            return 3;
        }
        if(methodType == EXIT_REENTRANT_LOCK) {
            return 4;
        }
        if(methodType == ENTER_READ_LOCK) {
            return 5;
        }
        if(methodType == EXIT_READ_LOCK) {
            return 6;
        }
        if(methodType == ENTER_WRITE_LOCK) {
            return 7;
        }
        if(methodType == EXIT_WRITE_LOCK) {
            return 8;
        }

        // MethodWithLock
        if(methodType == METHOD_WITH_READ_LOCK) {
            return 9;
        }
        if(methodType == METHOD_WITH_WRITE_LOCK) {
            return 10;
        }

        // NonBlockingMethod
        if(methodType == NON_BLOCKING_READ) {
            return 11;
        }
        if(methodType == NON_BLOCKING_WRITE) {
            return 12;
        }
        if(methodType == NON_BLOCKING_READ_WRITE) {
            return 13;
        }
        
        // Array
        if(methodType == ARRAY_NON_BLOCKING_READ) {
            return 14;
        }
        if(methodType == ARRAY_NON_BLOCKING_WRITE) {
            return 15;
        }
        if(methodType == ARRAY_NON_BLOCKING_READ_WRITE) {
            return 16;
        }
        
        
        if(methodType == NotYetImplementedMethod.SINGLETON) {
            return 17;
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
            return GET_READ_WRITE_LOCK;
        }

        // LockMethod
        if(id == 3) {
            return ENTER_REENTRANT_LOCK;
        }
        if(id == 4) {
            return EXIT_REENTRANT_LOCK;
        }
        if(id == 5) {
            return ENTER_READ_LOCK;
        }
        if(id == 6) {
            return EXIT_READ_LOCK;
        }
        if(id == 7) {
            return ENTER_WRITE_LOCK;
        }
        if(id == 8) {
            return EXIT_WRITE_LOCK;
        }

        // MethodWithLock
        if(id == 9) {
            return METHOD_WITH_READ_LOCK;
        }
        if(id == 10) {
            return METHOD_WITH_WRITE_LOCK;
        }

        // NonBlockingMethod
        if(id == 11) {
            return NON_BLOCKING_READ;
        }
        if(id == 12) {
            return NON_BLOCKING_WRITE;
        }
        if(id == 13) {
            return NON_BLOCKING_READ_WRITE;
        }
        
        // Array
        if(id == 14) {
            return ARRAY_NON_BLOCKING_READ;
        }
        if(id == 15) {
            return ARRAY_NON_BLOCKING_WRITE;
        }
        if(id == 16) {
            return ARRAY_NON_BLOCKING_READ_WRITE;
        }
        
        
        if(id == 17) {
            return NotYetImplementedMethod.SINGLETON;
        }

        throw new RuntimeException("unknown " + id);
    }

}
