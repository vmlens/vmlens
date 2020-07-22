package com.vmlens.trace.agent.bootstrap.event;

public interface GetStreamWithSlidingWindow {

	StreamWrapperWithSlidingWindow getStream(StreamRepository streamRepository);
	
	
}
