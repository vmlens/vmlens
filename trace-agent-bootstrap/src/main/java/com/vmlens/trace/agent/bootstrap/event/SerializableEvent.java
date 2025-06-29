package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.event.stream.StreamRepository;

public interface SerializableEvent {

    void serialize(StreamRepository streamRepository, LatestWrittenLoopAndRunId latestWrittenLoopAndRunId) throws Exception;

}
