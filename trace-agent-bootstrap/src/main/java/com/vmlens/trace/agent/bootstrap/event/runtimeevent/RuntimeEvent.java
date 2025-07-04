package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.event.LatestWrittenLoopAndRunId;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

import java.io.DataOutputStream;

public interface RuntimeEvent extends SerializableEvent, ParallelizeActionAfter {

    @Override
    default void serialize(StreamRepository streamRepository, LatestWrittenLoopAndRunId latestWrittenLoopAndRunId) throws Exception {
       if(latestWrittenLoopAndRunId.loopId() > loopId() || latestWrittenLoopAndRunId.runId() > runId()) {
           //System.err.println("wrong order for" +  this);
       } 
           
       latestWrittenLoopAndRunId.setLoopId(loopId());
       latestWrittenLoopAndRunId.setRunId(runId());
       serialize(getStream(streamRepository).getStream());
    }

    void setMethodCounter(PerThreadCounter perThreadCounter);
    void setThreadIndex(int threadIndex);

    int loopId();
    void setLoopId(int loopId);

    int runId();
    void setRunId(int runId);
    void setRunPosition(int runPosition);

    boolean isInterleaveActionFactory();
    void serialize(DataOutputStream buffer) throws Exception;
    StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository);



}
