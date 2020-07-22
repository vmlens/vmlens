package com.vmlens.trace.agent.bootstrap.parallize;

import com.vmlens.trace.agent.bootstrap.callback.AnarsoftWeakHashMap;

public class FutureTask2ThreadId {

	private final AnarsoftWeakHashMap<Long> task2ThreadId = new AnarsoftWeakHashMap<Long>();
	private final Object LOCK = new Object();
	
	private static final FutureTask2ThreadId instance = new FutureTask2ThreadId();
	
	
	private Long getInternal(Object in)
	{
		synchronized(LOCK)
		{
			return task2ThreadId.get(in);
		}
	}
	
	
	private void putInternal(Object in, long threadId)
	{
		synchronized(LOCK)
		{
			 task2ThreadId.put(in, threadId);
		}
	}
	
	
	public static Long get(Object in)
	{
		return instance.getInternal(in);
		
		
	}
	
	
	public static void put(Object task, long threadId)
	{
		instance.putInternal(task , threadId );
	}
	
	
	
}
