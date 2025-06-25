package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface LockExitOrWaitEvent extends ParallelizeActionAfter{

    Integer waitingThreadIndex();

}
