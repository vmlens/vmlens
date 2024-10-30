package com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;

import java.util.Iterator;

public class IteratorCallback {
	
	
	public static boolean hasNext(Iterator iter , int methodId)
	{
		if( iter.getClass().getName().startsWith("java.util.HashMap$") || iter.getClass().getName().startsWith("java.util.HashSet$") )
		{
			DelegateRepository.access(iter ,  MemoryAccessType.IS_READ  , methodId);
			
			
		}
		
			
		
		return iter.hasNext();
		
	}
	
	
	
	public static Object next(Iterator iter , int methodId)
	{
		if( iter.getClass().getName().startsWith("java.util.HashMap$") || iter.getClass().getName().startsWith("java.util.HashSet$") )
		{
			DelegateRepository.access(iter ,  MemoryAccessType.IS_READ  , methodId);
		}
		
		
		return iter.next();
		
		
	}
	

}
