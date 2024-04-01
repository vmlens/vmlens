package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;

public interface RuntimeEvent extends StaticEvent, InterleaveActionWithPositionFactory {

    void send(SendEventContext context);

    // For Test
    void serialize(StreamWrapperWithSlidingWindow streamWrapperWithSlidingWindow) throws Exception;

}
