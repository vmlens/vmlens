package com.vmlens.trace.agent.bootstrap.event;





import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

public abstract class AbstractStreamWrapper implements TLinkable<AbstractStreamWrapper>  {

	
	
	
	

	private AbstractStreamWrapper previous;
	private AbstractStreamWrapper next;
	//private int lastWrittenSlidingWindow = -1; 
	
	
	public AbstractStreamWrapper( TLinkedList<AbstractStreamWrapper> list) {
		super();
		
		list.add(this);
	}

	
	
	public AbstractStreamWrapper() {
		super();
	
	}



	
	

	
	
	public abstract void flush() throws Exception;
	public abstract void close() throws Exception;



	public AbstractStreamWrapper getPrevious() {
		return previous;
	}



	public void setPrevious(AbstractStreamWrapper previous) {
		this.previous = previous;
	}



	public AbstractStreamWrapper getNext() {
		return next;
	}



	public void setNext(AbstractStreamWrapper next) {
		this.next = next;
	}



	



//	public void serializeEventWithSlidingWindowId(int slidingWindowId)  throws Exception {
//		
//		
//		if(lastWrittenSlidingWindow != slidingWindowId )
//		{
//			 getStream();
//			
//			 
//			 streamStatistic.writeInt(slidingWindowId);
//			 streamStatistic.writeInt(  stream.size() );
//			 
//			 
//			lastWrittenSlidingWindow = slidingWindowId;
//		}
//		
//	}
	
	
	
	
	
	
	
	
	
	
}
