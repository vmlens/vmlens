package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.EitherPluginEventOnlyOrInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;
import gnu.trove.list.linked.TIntLinkedList;

public interface InterleaveRunState {

    int activeThreadIndex(TIntLinkedList activeThreadIndices);


    InterleaveRunState after(ProcessEventContext context,
                             AfterCallback afterCallback,
                             EitherPluginEventOnlyOrInterleaveActionFactory runtimeEvent);

    InterleaveRunState onBlockedWithLogging(ThreadIndexAndThreadStateMap runContext,
                                            SendEvent sendEvent,
                                            int blockedThreadIndex);

    StateAndIsActive isActive(int threadIndex);

}
