package com.vmlens.trace.agent.bootstrap.event;

import java.util.Iterator;

import gnu.trove.list.linked.TLinkedList;

public class StreamRepository {
	
	

	
	

	
	public final StreamWrapperWithoutSlidingWindow threadName;
	public final StreamWrapperWithoutSlidingWindow description;
	public final StreamWrapperWithoutSlidingWindow stackTrace;
	public final StreamWrapperWithoutSlidingWindow agentLog;
	public final StreamWrapperWithoutSlidingWindow className;
	
	
	
	
	public final StreamWrapperWithSlidingWindow firstWrite;
	public final StreamWrapperWithSlidingWindow method;
	public final StreamWrapperWithSlidingWindow syncActions;
	public final StreamWrapperWithSlidingWindow field;
	public final StreamWrapperWithSlidingWindow monitor;
	public final StreamWrapperWithSlidingWindow directMemory;
//	public final StreamWrapperWithSlidingWindow methodCoverage;
	public final StreamWrapperWithSlidingWindow scheduler;
	public final StreamWrapperWithSlidingWindow transfer;
	public final StreamWrapperWithSlidingWindow state;
	public final StreamWrapperWithSlidingWindow stateInitial;
	
	
	private final TLinkedList<AbstractStreamWrapper> streamList = new TLinkedList<AbstractStreamWrapper>();
	
	
	
	
	
	public StreamRepository(String eventDir, int writerNumber)
	{
		this.threadName = new StreamWrapperWithoutSlidingWindow(eventDir , "threadName" , writerNumber ,streamList);
		this.description = new StreamWrapperWithoutSlidingWindow(eventDir , "description" ,  writerNumber,streamList);
		this.stackTrace = new StreamWrapperWithoutSlidingWindow(eventDir , "stackTrace" ,  writerNumber,streamList);
		this.agentLog  = new StreamWrapperWithoutSlidingWindow(eventDir , "agentLog" ,  writerNumber,streamList);
		this.className  = new StreamWrapperWithoutSlidingWindow(eventDir , "className" ,  writerNumber,streamList);
		
		this.firstWrite = new StreamWrapperWithSlidingWindow(eventDir ,  "firstWrite" , writerNumber,streamList);
		this.method = new StreamWrapperWithSlidingWindow(eventDir , "method" ,  writerNumber , streamList);
		this.syncActions = new StreamWrapperWithSlidingWindow(eventDir , "syncActions" , writerNumber ,streamList);
		this.field = new StreamWrapperWithSlidingWindow(eventDir , "field" , writerNumber,streamList);
		this.monitor = new StreamWrapperWithSlidingWindow(eventDir , "monitor" , writerNumber,streamList);
		this.directMemory = new StreamWrapperWithSlidingWindow(eventDir , "directMemory" , writerNumber,streamList);
		//this.methodCoverage = new StreamWrapperWithSlidingWindow(eventDir , "methodCoverage" , writerNumber,streamList);
		
		this.scheduler = new StreamWrapperWithSlidingWindow(eventDir , "scheduler" , writerNumber,streamList);
		this.transfer = new StreamWrapperWithSlidingWindow(eventDir , "transfer" , writerNumber,streamList);
		
		this.state = new StreamWrapperWithSlidingWindow(eventDir , "state" , writerNumber,streamList);
		this.stateInitial = new StreamWrapperWithSlidingWindow(eventDir , "stateInitial" , writerNumber,streamList);
	}
	
	
	
	public void flush()  throws Exception
	{
		  Iterator<AbstractStreamWrapper> iterator = streamList.iterator();
		   
		   while(iterator.hasNext())
		   {
			   iterator.next().flush();
		   }
		
		
	}
	

	
	public void close() throws Exception
	{
	   Iterator<AbstractStreamWrapper> iterator = streamList.iterator();
	   
	   while(iterator.hasNext())
	   {
		   iterator.next().close();
	   }
		   
	   
		
		
	}
	
	

	

}
