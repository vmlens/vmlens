package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.threadlocal.ParallelizeBridgeForCallbackImpl;


/**
 * The methods of this class are interleaved to the bytecode of the application.
 * Fixme ove to callbacks
 */

public class ParallelizeCallback {

    public static void beforeThreadJoin(Thread toBeJoined) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        // Fixme
    }

    public static void callbackMethodEnter(int atomicId) {

    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        ThreadLocalForParallelize callbackStatePerThread = ParallelizeBridgeForCallbackImpl.callbackStatePerThread.get();
        // Fixme
    }

    public static void beginTask(int id) {


    }
}
