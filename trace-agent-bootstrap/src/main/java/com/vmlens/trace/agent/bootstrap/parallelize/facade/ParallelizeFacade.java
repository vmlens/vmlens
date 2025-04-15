package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.HasNextResult;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.emptyList;




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

    public void newTask(QueueIn queueIn,
                        ThreadLocalForParallelize threadLocalWrapperForParallelize,
                        RunnableOrThreadWrapper beganTask) {
        if (currentLoop != null) {
            try {
                threadLocalWrapperForParallelize.setInCallbackProcessing();
                currentLoop.newTask(queueIn, threadLocalWrapperForParallelize, beganTask);
            }
            finally{
                threadLocalWrapperForParallelize.stopCallbackProcessing();
            }
        }

    }

    public HasNextResult hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        try {
            threadLocalWrapperForParallelize.setInCallbackProcessing();
            TLinkedList<TLinkableWrapper<SerializableEvent>> serializableEvents = new TLinkedList<>();
            currentLoop = parallelizeLoopRepository.getOrCreate(obj, serializableEvents);
            boolean next = currentLoop.hasNext(threadLocalWrapperForParallelize, serializableEvents);
            return new HasNextResult(next, serializableEvents);
        }
        finally{
            threadLocalWrapperForParallelize.stopCallbackProcessing();
        }
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        currentLoop.close(threadLocalWrapperForParallelize);
        currentLoop = null;
    }
}
