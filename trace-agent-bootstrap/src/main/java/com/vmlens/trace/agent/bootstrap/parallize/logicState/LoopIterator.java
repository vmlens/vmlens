package com.vmlens.trace.agent.bootstrap.parallize.logicState;

public class LoopIterator {

	
	private LoopElement current;
	private final LoopElement start;
	
	
	public LoopIterator(LoopElement start) {
		super();
		this.start = start;
		current = start;
		
		
	}
	
	public boolean hasNext()
	{
		return current != null;
	}
	
	
	
	public LoopElement next()
	{
		LoopElement temp = current;
		
		current = current.next;
		
		if( current == start )
		{
			current  = null;
		}
		
		return temp;
	}
	
	
	
}
