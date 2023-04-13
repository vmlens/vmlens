package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public class ParallelizeLoopContainer {

    private final ParallelizeLoopRepository parallelizeLoopRepository;
    private ParallelizeLoop currentLoop;

    public ParallelizeLoopContainer(ParallelizeLoopFactory parallelizeLoopFactory) {
        this.parallelizeLoopRepository = new ParallelizeLoopRepository(parallelizeLoopFactory);
    }

    public boolean beginThreadMethodEnter(LoopThreadState loopThreadState,
                                          RunnableOrThreadWrapper beganTask) {
        if(currentLoop != null) {
            return currentLoop.beginThreadMethodEnter(loopThreadState,beganTask);
        }
        return false;
    }

    public  boolean hasNext(LoopThreadState loopThreadState, Object obj) {
        // ToDo if this is a new loop terminate the old one? or create a warning
        currentLoop = parallelizeLoopRepository.getOrCreate(obj);
        return currentLoop.hasNext(loopThreadState);
    }

    public  void close(LoopThreadState loopThreadState, Object obj) {
        // ToDo what to do when this is a different loop than the current?
        currentLoop.close(loopThreadState);
        currentLoop = null;
    }


}
