package com.vmlens.trace.agent.bootstrap.parallelize.facade;


import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.callback.CallbackStatePerThread;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.parallelize.command.DirectSyncActionParallelizeCommand;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.AllInterleavingsLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRun;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRunFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRunFactoryImpl;

/**
 * Responsible for creating sync actions. Basically the command design pattern.
 * called from ParallelizeCallback and other callbacks for example lock.
 */

public class ParallelizeFacade {

    public static final ParallelizeFacade SINGELTON = new ParallelizeFacade();

    private final AllInterleavingsLoopCollection allInterleavingsLoopCollection;

    public ParallelizeFacade() {
        this(new SynchronizationWrapperForRunFactoryImpl());
    }

    public ParallelizeFacade(SynchronizationWrapperForRunFactory synchronizationWrapperForRunFactory) {
        allInterleavingsLoopCollection = new AllInterleavingsLoopCollection(synchronizationWrapperForRunFactory);
    }

    // Functions called from extern
    public boolean advance(CallbackStatePerThread callbackStatePerThread, AllInterleavings allInterleavings) {
        AllInterleavingsLoop loop = allInterleavingsLoopCollection.getOrCreate(allInterleavings);
        // callbackStatePerThread.parallelizedThreadLocal = new ParallelizedThreadLocal(loop.)
        SynchronizationWrapperForRun run = loop.advance();
        if (run != null) {
            callbackStatePerThread.parallelizedThreadLocal = new ParallelizedThreadLocal(run);
            return true;
        }

        return false;
    }

    public void beforeFieldAccessVolatile(CallbackStatePerThread callbackStatePerThread, long objectHashCode,
                                          int fieldId, int operation) {
        if (callbackStatePerThread.parallelizedThreadLocal != null) {
            callbackStatePerThread.parallelizedThreadLocal.before(
                    new DirectSyncActionParallelizeCommand(new VolatileFieldAccess(fieldId, operation)
                            , callbackStatePerThread.threadId));
        }

    }

    // Functions called from ParallelizeCallback


}
