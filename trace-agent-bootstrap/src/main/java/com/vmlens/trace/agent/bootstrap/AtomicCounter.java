package com.vmlens.trace.agent.bootstrap;

public class AtomicCounter {

	private int currentMax = 0;
	
	
	public synchronized OptionalByte nextValue()
	{
		
		if( currentMax > 255)
		{
			return new OptionalByte(false,(byte)0);
		}
		else
		{
			byte temp = (byte) currentMax;
			currentMax++;
			
			return new OptionalByte(true,temp);
		}
		
		
		
		
	}
	
	
	
	
}
