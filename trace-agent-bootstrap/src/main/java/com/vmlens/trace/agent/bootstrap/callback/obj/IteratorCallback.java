package com.vmlens.trace.agent.bootstrap.callback.obj;

import java.util.Iterator;

import com.vmlens.trace.agent.bootstrap.callback.AgentLogCallback;
import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

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
