package com.vmlens.trace.agent.bootstrap.callbackdeprecated.state;

public class ArrayState  {

	
	
	  public ArrayStateStatistic first;
	  public ArrayStateStatistic second;
	  public ArrayStateStatistic third;
	  public ArrayStateStatistic other;
	
	  
	  public ArrayStateStatistic getArrayStateStatistic(long threadId)
	  {
		  if( first == null )
		  {
			  first = new ArrayStateStatistic(threadId);
			  
			  return first;
		  }
		  
		  if( first.threadId == threadId )
		  {
			  return first;
		  }
		  
		  if( second == null )
		  {
			  second = new ArrayStateStatistic(threadId);
			  
			  return second;
		  }
		  
		  if( second.threadId == threadId )
		  {
			  return second;
		  }
		  
		  
		  if( third == null )
		  {
			  third = new ArrayStateStatistic(threadId);
			  
			  return first;
		  }
		  
		  if( third.threadId == threadId )
		  {
			  return third;
		  }
		  
		  
		  
		  if( other == null )
		  {
			  other = new ArrayStateStatistic(-1L);
			  
			  return second;
		  }
		  
		  return other;
		  
	  }
	  
	
	
		public ArrayState(long id) {
		super();
		this.id = id;
	}


		public final long id;
		
	    private static long maxId = 0;
		
		public synchronized static long getNewId()
		{
			maxId++;
			return maxId;
		}
		
	
	
	
}
