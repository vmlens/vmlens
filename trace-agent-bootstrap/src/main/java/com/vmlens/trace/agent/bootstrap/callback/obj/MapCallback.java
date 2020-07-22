package com.vmlens.trace.agent.bootstrap.callback.obj;

import java.util.Map;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;

public class MapCallback {

	protected static void access(Object obj, int operation, int methodId)
	{
		HashMapCallback.objectCallbackState.access(obj ,operation , methodId);
	}
	
	
	public static void createDelegate(Object delegate , Object obj)
	{
		if( obj instanceof  java.util.HashMap )
		{
			HashMapCallback.createDelegate(delegate, obj);
		}
		
	
	}
	
	
//	public static Object get(Map map,Object key,int methodId)
//	{
//		HashMapCallback.objectCallbackState.access(map , MemoryAccessType.IS_READ , methodId);
//		
//		return map.get(key);
//	}
	
}
