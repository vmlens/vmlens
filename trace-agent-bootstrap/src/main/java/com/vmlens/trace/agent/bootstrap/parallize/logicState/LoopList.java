package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class LoopList {
	
	LoopElement start = null;
	

//	LoopIterator iterator()
//	{
//		return
//	}
	
	
	boolean onlyOneElement()
	{
		if( start == null )
		{
			return false;
		}
		else
		{
			return start == start.next;
		}
		
	}
	
	
	LoopElement add(long threadId )
	 {
		 if( start == null )
		 {
			 start = new LoopElement(threadId);
			 start.next = start;
			 start.prevoius = start;
		
			 
			 return start;
		 }
		 else
		 {
			 LoopElement n  = new LoopElement(threadId);
			 n.next = start;
			 n.prevoius =  start.prevoius;
			 n.prevoius.next = n;
			 start.prevoius = n;
			 
			 return n;
			
		 }
		 
		 
	 }
	
	
	void remove(LoopElement element)
	{
		if( start.next == start )
		{
			start = null;
		}
		else
		{
			element.prevoius.next = element.next;
			element.next.prevoius = element.prevoius;
			
			if(element == start)
			{
				start = element.next;
			}
			
			
		}
	}
	
	
}
