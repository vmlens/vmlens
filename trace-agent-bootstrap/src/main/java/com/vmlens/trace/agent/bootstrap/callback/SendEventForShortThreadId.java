package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.gen.EventFactory;

public class SendEventForShortThreadId  extends SendEventImpl {

	private final short shortThreadId;

	
	public SendEventForShortThreadId(long myThreadId,QueueCollectionWrapper queueCollection, short shortThreadId,CallbackStatePerThread callbackStatePerThread) {
		super(myThreadId, queueCollection,callbackStatePerThread);
		this.shortThreadId = shortThreadId;
	}
	
	
	public void writeMethodEnterEventGen(final int slidingWindowId, final int methodId, final int methodCounter) {
		getQueueCollection().put( ID_Method , 

				EventFactory.createMethodEnterShortThreadIdEventGen( slidingWindowId, shortThreadId, methodId,methodCounter)  , slidingWindowId);

		
		
	}

	public void writeMethodExitEventGen(final int slidingWindowId, final int methodId,final  int methodCounter) {
		getQueueCollection().put( ID_Method , 

				EventFactory.createMethodExitShortThreadIdEventGen( slidingWindowId, shortThreadId, methodId,methodCounter) , slidingWindowId );

	}
	
}
