package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

/**
 * The methods of this class are interleaved to the bytecode of the application.
 */

public class ParallelizeCallback {

    public static void beforeThreadJoin(Thread toBeJoined) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.after(callbackStatePerThread, ParallelizeFacade.parallelizeActionFactory().threadJoin(toBeJoined.getId()));
    }

    public static void callbackMethodEnter(int atomicId) {

    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.after(callbackStatePerThread, ParallelizeFacade.parallelizeActionFactory().fieldAccess(fieldId, operation));
    }
    public static void afterThreadStart() {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        ParallelizeFacade.afterThreadStart(callbackStatePerThread);
    }

    public static void beginTask(int id) {


    }
}
