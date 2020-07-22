package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.event.gen.AbstractSendEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallize.ParallizedThreadFacade;
import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;

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
	protected ParallizedThreadFacade getParallizedThreadFacade() {
	
		return callbackStatePerThread.parallizedThread;
	}
//

	
	
	
	
	/*
	 * public static void writeFirstWriteFieldAccessEventGen (ArrayEvent arrayEvent, int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  boolean     isWrite
,  int     methodId
,  boolean     stackTraceIncomplete
,  long     objectHashCode
,  int     slidingWindowIdForFirstWrite
) (non-Javadoc)
	 * @see com.vmlens.trace.agent.bootstrap.callback.SendEventComplete#writeFirstWriteFieldAccessEventGen(long, int, int, int, int, boolean, int, boolean, long)
	 */

	
	
	/*
	 * public static void writeFirstWriteFieldAccessEventStaticGen (ArrayEvent arrayEvent, int slidingWindowId
,  long     threadId
,  int     programCounter
,  int     fieldId
,  int     methodCounter
,  boolean     isWrite
,  int     methodId
,  boolean     stackTraceIncomplete
,  int     slidingWindowIdForFirstWrite
) (non-Javadoc)
	 * @see com.vmlens.trace.agent.bootstrap.callback.SendEventComplete#writeFirstWriteFieldAccessEventStaticGen(long, int, int, int, int, boolean, int, boolean)
	 */

	
}
