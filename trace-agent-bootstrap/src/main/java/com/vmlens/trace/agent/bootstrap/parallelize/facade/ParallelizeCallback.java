package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

/**
 * The methods of this class are interleaved to the bytecode of the application.
 */

public class ParallelizeCallback {


    public static void beforeThreadJoin(Thread toBeJoined) {

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

    public static void afterMonitor() {

    }

    public static void afterMethod() {

    }

    public static void afterThreadStart() {


    }

    public static void beginTask(int id) {


    }
}
