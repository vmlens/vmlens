package com.vmlens.trace.agent.bootstrap.parallelize;


import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;

/**
 * Responsible for creating sync actions. Basically the command design pattern.
 * called from ParallelizeCallback and other callbacks for example lock.
 */

public class ParallelizeFacade {

    private final AllInterleavingsLoopCollection controller = new AllInterleavingsLoopCollection();

    // Functions called from extern
    public boolean advance(CallbackStatePerThread callbackStatePerThread, AllInterleavings allInterleavings) {
        return false;
    }

    // Functions called from ParallelizeCallback


}
