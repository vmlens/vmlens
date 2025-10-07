package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.ParallelizeLoopRepository;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ParallelizeLoopFactoryImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;


public class ParallelizeFacade {

    private static volatile ParallelizeFacade parallelizeFacade =
            new ParallelizeFacade(new ParallelizeLoopRepository(new ParallelizeLoopFactoryImpl()));
    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private volatile ObjectAndParallelizeLoop currentLoop;


    public static ParallelizeFacade parallelize() {
        return parallelizeFacade;
    }

    public ParallelizeFacade(ParallelizeLoopRepository parallelizeLoopRepository) {
        this.parallelizeLoopRepository = parallelizeLoopRepository;
    }

    public void newTask(QueueIn queueIn,
                        ThreadLocalForParallelize threadLocalWrapperForParallelize,
                        ThreadWrapper beganTask) {
        if (currentLoop != null) {
            currentLoop.loop().newTask(queueIn, threadLocalWrapperForParallelize, beganTask);
        }
    }

    public boolean hasNext(ThreadLocalForParallelize threadLocalWrapperForParallelize, QueueIn queueIn, Object obj) {
        if(currentLoop != null) {
            if(currentLoop.object() == obj ) {
                return currentLoop.loop().hasNext(threadLocalWrapperForParallelize, queueIn);
            }
            parallelizeLoopRepository.remove(currentLoop.object());
        }

        currentLoop = new ObjectAndParallelizeLoop(obj, parallelizeLoopRepository.getOrCreate(obj, queueIn));
        return currentLoop.loop().hasNext(threadLocalWrapperForParallelize, queueIn);
    }

    public void close(ThreadLocalForParallelize threadLocalWrapperForParallelize, Object obj) {
        currentLoop.loop().close(threadLocalWrapperForParallelize);
        parallelizeLoopRepository.remove(currentLoop.object());
        currentLoop = null;
    }
}
