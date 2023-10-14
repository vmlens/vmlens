package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeActionFactory.action;
import static com.vmlens.trace.agent.bootstrap.parallelize.facade.ParallelizeFacade.parallelize;

/**
 * The methods of this class are interleaved to the bytecode of the application.
 */

public class ParallelizeCallback {

    public static void beforeThreadJoin(Thread toBeJoined) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        parallelize().after(callbackStatePerThread, action().threadJoin(toBeJoined.getId()));
    }

    public static void callbackMethodEnter(int atomicId) {

    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        parallelize().after(callbackStatePerThread, action().fieldAccess(fieldId, operation));
    }
    public static void afterThreadStart() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        parallelize().afterThreadStart(callbackStatePerThread);
    }

    public static void beginTask(int id) {


    }
}
