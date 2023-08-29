package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

/**
 * The methods of this class are interleaved to the bytecode of the application.
 */

public class ParallelizeCallback {


    public static void beforeThreadJoin(Thread toBeJoined) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.beforeThreadJoin(callbackStatePerThread, toBeJoined.getId());
    }

    public static void afterThreadJoin(Thread toBeJoined, long millis) {

    }

    public static void callbackMethodEnter(int atomicId) {


    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.afterFieldAccessVolatile(callbackStatePerThread, fieldId, operation);
    }
    public static void afterThreadStart() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.afterThreadStart(callbackStatePerThread);
    }

    public static void beginTask(int id) {


    }
}
