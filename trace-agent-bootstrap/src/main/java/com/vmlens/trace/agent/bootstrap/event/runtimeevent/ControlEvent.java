package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.event.LatestWrittenLoopAndRunId;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

import java.io.DataOutputStream;

/**
 * run start, run end and warning
 */
public interface ControlEvent extends SerializableEvent  {

    @Override
    default void serialize(StreamRepository streamRepository, LatestWrittenLoopAndRunId latestWrittenLoopAndRunId) throws Exception {
        if(latestWrittenLoopAndRunId.loopId() > loopId() || latestWrittenLoopAndRunId.runId() > runId()) {
         //   System.err.println("wrong order for" +  this);
        }

        latestWrittenLoopAndRunId.setLoopId(loopId());
        latestWrittenLoopAndRunId.setRunId(runId());
        serialize(getStream(streamRepository).getStream());
    }

    int loopId();
    int runId();

    void serialize(DataOutputStream buffer) throws Exception;
    StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository);

}
