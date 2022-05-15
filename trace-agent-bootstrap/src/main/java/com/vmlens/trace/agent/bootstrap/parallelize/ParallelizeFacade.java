package com.vmlens.trace.agent.bootstrap.parallelize;


import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsRunFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsRunFactoryImpl;

/**
 * Responsible for creating sync actions. Basically the command design pattern.
 * called from ParallelizeCallback and other callbacks for example lock.
 */

public class ParallelizeFacade {

    public static final ParallelizeFacade SINGELTON = new ParallelizeFacade();


    private final AllInterleavingsLoopCollection controller;

    public ParallelizeFacade() {
        this(new AllInterleavingsRunFactoryImpl());
    }

    // Fixme zirkulare referenz Package Design
    public ParallelizeFacade(AllInterleavingsRunFactory allInterleavingsRunFactory) {
        controller = new AllInterleavingsLoopCollection(allInterleavingsRunFactory);
    }

    // Functions called from extern
    public boolean advance(CallbackStatePerThread callbackStatePerThread, AllInterleavings allInterleavings) {
        return false;
    }

    public void beforeFieldAccessVolatile(CallbackStatePerThread callbackStatePerThread, long objectHashCode,
                                          int fieldId, int operation) {

    }

    // Functions called from ParallelizeCallback


}
