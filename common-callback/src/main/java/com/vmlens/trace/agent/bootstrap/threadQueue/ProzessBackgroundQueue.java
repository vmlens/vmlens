package com.vmlens.trace.agent.bootstrap.threadQueue;

import org.jctools.queues.MessagePassingQueue.Consumer;

public class ProzessBackgroundQueue implements Consumer<Object> {

	private final SingleEventReader singleEventReader;
	
	boolean stopEventReceived = false;
	int prozessedEvents;
	
	public ProzessBackgroundQueue(SingleEventReader singleEventReader) {
		super();
		this.singleEventReader = singleEventReader;
	}





	@Override
	public void accept(Object e) {
		
		if( e ==  singleEventReader.queueFacade.STOP_EVENT)
		{
			stopEventReceived = true;
		}
		else if( e instanceof ArrayEvent )
		{
			ArrayEvent arrayEvent = (ArrayEvent) e;
			
			for(int i = 0 ; i< arrayEvent.length ; i++)
			{
				singleEventReader.eventSink.consume( arrayEvent.array[i] );
			}
			
			prozessedEvents += arrayEvent.length;
			
		}
		else
		{
			prozessedEvents++;
			singleEventReader.eventSink.consume( e );
		}
		
		
		
	}

}
