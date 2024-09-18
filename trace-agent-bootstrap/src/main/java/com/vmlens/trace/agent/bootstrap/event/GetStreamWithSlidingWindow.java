package com.vmlens.trace.agent.bootstrap.event;

public interface GetStreamWithSlidingWindow {

    StreamWrapperWithLoopIdAndRunId getStream(StreamRepository streamRepository);
	
	
}
