package com.vmlens.trace.agent.bootstrap;

public class AtomicCounterShort {

	private int currentMax = 0;
	
	
	public synchronized OptionalShort nextValue()
	{
		
		if( currentMax > 65535)
		{
			return new OptionalShort(false,(short)0);
		}
		else
		{
			short temp = (short) currentMax;
			currentMax++;
			
			return new OptionalShort(true,temp);
		}
		
		
		
		
	}
	
	
}
