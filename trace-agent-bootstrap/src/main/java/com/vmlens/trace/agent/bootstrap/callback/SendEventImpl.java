package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.gen.AbstractSendEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;

public class SendEventImpl extends AbstractSendEvent implements SendEvent {

	private final long myThreadId;
	private final QueueCollectionWrapper queueCollection;
	private final CallbackStatePerThread callbackStatePerThread;
	
	
	public SendEventImpl(long myThreadId, QueueCollectionWrapper queueCollection, CallbackStatePerThread callbackStatePerThread) {
		super();
		this.myThreadId = myThreadId;
		this.queueCollection = queueCollection;
		this.callbackStatePerThread = callbackStatePerThread;
	}

	@Override
	protected long threadId() {
		return myThreadId;
	}

	public QueueCollectionWrapper getQueueCollection() {
		return queueCollection;
	}

    @Override
    protected ParallelizedThreadLocal getParallelizedThreadLocal() {
        return callbackStatePerThread.getParallelizedThreadLocal();
    }


}
