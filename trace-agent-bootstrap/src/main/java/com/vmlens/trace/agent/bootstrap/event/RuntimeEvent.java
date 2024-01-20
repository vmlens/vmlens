package com.vmlens.trace.agent.bootstrap.event;

public interface RuntimeEvent extends StaticEvent {

    void send(SendEventContext context);

    // For Test
    void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception;

}
