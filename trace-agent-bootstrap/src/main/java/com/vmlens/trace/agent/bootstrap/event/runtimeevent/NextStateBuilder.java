package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public interface NextStateBuilder {

    void addWaitingThreadIndex(int threadIndex);
    void addExitEvent();
    int addThreadStarted(ThreadWrapper startedThread);

}
