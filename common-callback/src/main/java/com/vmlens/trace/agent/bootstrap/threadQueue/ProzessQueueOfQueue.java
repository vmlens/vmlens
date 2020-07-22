package com.vmlens.trace.agent.bootstrap.threadQueue;

import org.jctools.queues.MessagePassingQueue.Consumer;

import gnu.trove.list.linked.TLinkedList;

public class ProzessQueueOfQueue implements Consumer<InternalPerThreadQueue>  {

	private final SingleEventReader singleEventReader;
   
	
	
	
	public ProzessQueueOfQueue(SingleEventReader singleEventReader) {
		super();
		this.singleEventReader = singleEventReader;
	}

	@Override
	public void accept(InternalPerThreadQueue e) {
		singleEventReader.queueList.add(e);
		
	}
	
	
	
	
}
