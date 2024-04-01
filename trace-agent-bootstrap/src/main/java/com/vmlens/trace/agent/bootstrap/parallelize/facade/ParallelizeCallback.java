package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.CallbackState;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThreadForParallelize;



/**
 * The methods of this class are interleaved to the bytecode of the application.
 * Fixme ove to callbacks
 */

public class ParallelizeCallback {

    public static void beforeThreadJoin(Thread toBeJoined) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        // Fixme
    }

    public static void callbackMethodEnter(int atomicId) {

    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        // Fixme
    }
    public static void afterThreadStart() {
        CallbackStatePerThreadForParallelize callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        // Fixme
    }

    public static void beginTask(int id) {


    }
}
