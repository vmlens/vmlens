package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.gen.EventFactory;

import static com.vmlens.trace.agent.bootstrap.event.gen.AbstractSendEvent.ID_Method;

public class SendEventForSmallThreadId extends SendEventImpl {

	private final byte mappedThreadId;

	public SendEventForSmallThreadId(long myThreadId,QueueCollectionWrapper queueCollection, byte mappedThreadId,CallbackStatePerThread callbackStatePerThread) {
		super(myThreadId, queueCollection,callbackStatePerThread);
		this.mappedThreadId = mappedThreadId;

	}

	public void writeMethodEnterEventGen(final int slidingWindowId, final int methodId, final int methodCounter) {
		getQueueCollection().put( ID_Method , 

				EventFactory.createMethodEnterSmallThreadIdEventGen( slidingWindowId, mappedThreadId, methodId,methodCounter), slidingWindowId);

		
		
	}

	public void writeMethodExitEventGen(final int slidingWindowId, final int methodId,final  int methodCounter) {
		getQueueCollection().put( ID_Method , 

				EventFactory.createMethodExitSmallThreadIdEventGen( slidingWindowId, mappedThreadId, methodId,methodCounter), slidingWindowId);

	}

}
