package com.vmlens.trace.agent.bootstrap.threadQueue;

import java.util.concurrent.ThreadFactory;



import org.jctools.queues.MessagePassingQueue;
import org.jctools.queues.MpscLinkedQueue;

import java.util.Queue;

/**
 * 
 * namensgebung: put, poll()
 * 
 * put klappt immer
 * poll liefert null zurück
 * 
 * siehe https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/BlockingQueue.html
 * 
 * 
 * 
 * @author thomas
 *
 */



public class QueueFacade {

	
	private volatile EventSink  eventSink;
	
	
	final MessagePassingQueue backroundQueue;
	final MessagePassingQueue<InternalPerThreadQueue> queueOfQueues;
	

	
	final Object STOP_EVENT = new Object();
	
	private final InternalPerThreadQueueFactory[] queueCollectionFactoryArray;
	
	volatile long readCount = 0;
	volatile int  writeCount=0;
	
	private final Object WRITE_COUNT_LOCK = new Object();
	
	private volatile Thread workerThread;
	
	public QueueFacade(InternalPerThreadQueueFactory[] queueCollectionFactoryArray) {
		super();
				
		backroundQueue =  MpscLinkedQueue.newMpscLinkedQueue();
	    queueOfQueues =  MpscLinkedQueue.newMpscLinkedQueue();
		
		this.queueCollectionFactoryArray = queueCollectionFactoryArray;
		
	
		
	}


	public void putDirect(Object in)
	{
		backroundQueue.offer(in);
	}
	
	
	public QueueCollection createQueueCollection4ThreadLocal()
	{
		PerThreadQueue[]  perThreadQueueArray = new  PerThreadQueue[queueCollectionFactoryArray.length];
		 
		 for(int i = 0 ; i < queueCollectionFactoryArray.length ; i++)
		 {
			 perThreadQueueArray[i] = new PerThreadQueue(queueCollectionFactoryArray[i]);
		 }

		return new QueueCollection(this  , perThreadQueueArray);
	}
	
	
	public void start(EventSink  consumer,ThreadFactory threadFactory)
	{
		eventSink = consumer;
		
		 workerThread = threadFactory.newThread(new SingleEventReader(this,consumer));
		 workerThread.start();
	}
	
	
	public void stop() throws InterruptedException
	{
		backroundQueue.offer(STOP_EVENT);
		
		if( workerThread != null )
		{
			workerThread.join();
		}
		
	}
	
//	void internalPerThreadQueueFull()
//	{
//		if(eventSink != null )
//		{
//			eventSink.internalPerThreadQueueFull();
//		}
//	}


	public void increamentWriteCount() {
		synchronized(WRITE_COUNT_LOCK)
		{
			writeCount++;
		}
		
	}
	
	
	
}
