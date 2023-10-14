package com.vmlens.trace.agent.bootstrap.event;

public interface RuntimeEvent extends StaticEvent {


    void setRunLoopAndSlidingWindowId(int runId, int loopId, int slidingWindowId);

    void send(SendEventContext context);

}
