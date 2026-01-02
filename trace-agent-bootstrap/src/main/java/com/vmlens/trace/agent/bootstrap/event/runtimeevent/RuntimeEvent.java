package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.SerializableEvent;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;
import com.vmlens.trace.agent.bootstrap.event.stream.StreamWrapperWithLoopIdAndRunId;

import java.io.DataOutputStream;

public interface RuntimeEvent extends SerializableEvent, EitherPluginEventOnlyOrInterleaveActionFactory {

    @Override
    default void serialize(StreamRepository streamRepository) throws Exception {
       serialize(getStream(streamRepository).getStream());
    }

    /**
     * sets the method and dominator tree counter
     * @param perThreadCounter
     */
    void setCounter(PerThreadCounter perThreadCounter);
    void setThreadIndex(int threadIndex);

    int loopId();
    void setLoopId(int loopId);

    int runId();
    void setRunId(int runId);
    void setRunPosition(int runPosition);

    void serialize(DataOutputStream buffer) throws Exception;
    StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository);




}
