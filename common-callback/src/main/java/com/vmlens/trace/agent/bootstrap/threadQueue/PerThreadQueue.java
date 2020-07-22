package com.vmlens.trace.agent.bootstrap.threadQueue;

/**
 * 
 * reader thread
 * 		state: idle -> stopped
 * 
 * 
 * writer thread
 * 	-> current new setzen wenn state = stopped
 * 
 * 
 * 
 * @author thomas
 *
 */



public class PerThreadQueue {

	
	private final InternalPerThreadQueueFactory internalPerThreadQueueFactory;
	
	
	
	
	
	public PerThreadQueue(InternalPerThreadQueueFactory internalPerThreadQueueFactory) {
		super();
		this.internalPerThreadQueueFactory = internalPerThreadQueueFactory;
	}


	private InternalPerThreadQueue current;
	
	
	private void createNewInternalPerThreadQueue(Object element,int slidingWindowId,QueueFacade queueFacade)
	{
		current = internalPerThreadQueueFactory.create(slidingWindowId, element);
		queueFacade.queueOfQueues.offer( current );
		
	}
	
	
	/**
	 * 
	 *     wenn null
	 *     		
	 * 
	 * 
	 *      wenn  nich null
	 * 
	 * 
		 						setze auf writing
		 							cas nicht erfolgreich
		 								check slidindWindowId
		 									wenn veraltet direkt in queue schreiben
		 									ansonsten neue queue anlegen und dort hineinschreiben
		 							
		 						
		 							check current.slidingWindowId 
		 								!= slidingWindowId
		 									queue leeren
		 									aufstop setzen
		 									neue queue anlegen und dort hineinschreiben
		 								== slidingWindowId
		 									in aktuelle queue schreiben
		 										wenn voll leeren
		 									auf idle setzen
		 				check auf size background ueue
	 */
	public void put(Object element,int slidingWindowId,QueueFacade queueFacade)
	{
		if(  current == null )
		{
			createNewInternalPerThreadQueue( element, slidingWindowId ,  queueFacade );
		}
		else
		{
			if(  current.set4Writing() )
			{
				if(current.forSlidingWindowId <  slidingWindowId)
				{
					current.stopByWriterThread(queueFacade);
					createNewInternalPerThreadQueue( element, slidingWindowId ,  queueFacade );
				}
				else
				{
					current.put(element, queueFacade);
					current.state = InternalPerThreadQueue.IDLE;
				}
			}
			else
			{
				// nicht erfolgreich, queue wurde gestoppt
				
				if( current.forSlidingWindowId <  slidingWindowId )
				{
					createNewInternalPerThreadQueue( element, slidingWindowId ,  queueFacade );
				}
				else
				{
					// aktuelles event ist veraltet, direkt in die queue schreiben
					queueFacade.backroundQueue.offer(element);
				}
				
				
				
			}
			
			
			
		}
		
		
		
		
	}
	
	
}
