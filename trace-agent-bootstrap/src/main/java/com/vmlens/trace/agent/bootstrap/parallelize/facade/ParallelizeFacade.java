package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;


/**
 * Fixme synchronization
 */


public class ParallelizeFacade {

    private static volatile ParallelizeFacade parallelizeFacade =
            new ParallelizeFacade(new ParallelizeLoopRepository(new ParallelizeLoopFactoryImpl()));
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private volatile ParallelizeLoop currentLoop;


    public static ParallelizeFacade parallelize() {
        return parallelizeFacade;
    }

    public ParallelizeFacade(ParallelizeLoopRepository parallelizeLoopRepository) {
        this.parallelizeLoopRepository = parallelizeLoopRepository;
    }

    public TLinkedList<TLinkableWrapper<SerializableEvent>> newTask(ThreadLocalForParallelize threadLocalWrapperForParallelize,
                                                                    RunnableOrThreadWrapper beganTask) {
        if (currentLoop != null) {
            return currentLoop.newTask(threadLocalWrapperForParallelize, beganTask);
        }
        return emptyList();
    }

    public HasNextResult hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = new TLinkedList<>();
        currentLoop = parallelizeLoopRepository.getOrCreate(obj, serializableEvents);
        boolean next = currentLoop.hasNext(threadLocalWrapperForParallelize, serializableEvents);
        return new HasNextResult(next, serializableEvents);
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        currentLoop.close(threadLocalWrapperForParallelize);
        currentLoop = null;
    }
}
