package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapterImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;


/**
 * The methods of this class are interleaved to the bytecode of the application.
 * Fixme ove to callbacks
 */

public class ParallelizeCallback {

    public static void beforeThreadJoin(Thread toBeJoined) {
        ThreadLocalForParallelize callbackStatePerThread = ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();
        // Fixme
    }

    public static void callbackMethodEnter(int atomicId) {

    }

    public static void callbackMethodExit() {

    }
    public static void afterFieldAccess(int fieldId, int operation) {
        ThreadLocalForParallelize callbackStatePerThread = ThreadLocalWhenInTestAdapterImpl.callbackStatePerThread.get();
        // Fixme
    }

    public static void beginTask(int id) {


    }
}
