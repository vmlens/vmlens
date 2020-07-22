package com.vmlens.trace.agent.bootstrap.callback.obj;

import com.vmlens.trace.agent.bootstrap.util.Constants;

public class HashSetCallback {

	
static final ObjectCallbackState objectCallbackState = new ObjectCallbackState( Constants.FIELD_ID_JAVA_UTIL_HASH_SET );
	
	
	
	protected static void access(Object obj, int operation, int methodId)
	{
		objectCallbackState.access(obj , operation , methodId);
	}
	
	public static void createDelegate(Object delegate , Object obj)
	{
		DelegateRepository.put(delegate, obj , objectCallbackState );
	}
	
}
