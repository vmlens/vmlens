package com.vmlens.trace.agent.bootstrap.callback;


import com.vmlens.trace.agent.bootstrap.threadQueue.QueueCollection;
import com.vmlens.trace.agent.bootstrap.util.Constants;


public class QueueCollectionWrapper {

	private final QueueCollection queueCollection;
	public QueueCollectionWrapper(QueueCollection queueCollection) {
		super();
		this.queueCollection = queueCollection;
	}
	
	public void putDirect(Object in)
	{
		queueCollection.putDirect(in);
	}
	int writeEventCount = 0;

	public void put(int index, Object element, int slidingWindowId) {
        CallbackStatePerThread callbackStatePerThread = CallbackState.callbackStatePerThread.get();
        callbackStatePerThread.stackTraceBasedDoNotTrace++;
        queueCollection.put(index, element, slidingWindowId);
        writeEventCount++;

        if (writeEventCount >= Constants.WRITE_WRITE_COUNT_EVERY_X) {
            writeEventCount = 0;
            queueCollection.increamentWriteCount();
        }
        while (queueCollection.getQueueLength() > 100000) {
            queueCollection.park(10);
            callbackStatePerThread.queueIsFull = true;
        }
		callbackStatePerThread.stackTraceBasedDoNotTrace--;  
	}
}