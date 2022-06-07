package com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop;

import com.vmlens.trace.agent.bootstrap.Logger;

public interface SynchronizationWrapperForRunFactory {

    SynchronizationWrapperForRun create(AllInterleavingsLoop loop);

    Logger getLogger();

}
