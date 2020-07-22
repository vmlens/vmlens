package com.vmlens.trace.agent.bootstrap.event;



public interface RuntimeEvent {
	
	void serialize(StreamRepository streamRepository) throws Exception;
	void serialize2StreamWrapper(StreamWrapperWithSlidingWindow streamWrapper) throws Exception;
	int getSlidingWindowId();
	//void writeToStream(DataOutputStream stream) throws Exception;

	
	
	
	
}
