package com.vmlens.trace.agent.bootstrap.callback.obj;

import java.util.HashMap;
import java.util.Set;

import com.vmlens.trace.agent.bootstrap.util.Constants;;


public class HashMapCallback {

	static final ObjectCallbackState objectCallbackState = new ObjectCallbackState( Constants.FIELD_ID_JAVA_UTIL_HASH_MAP );
	
	
	
	protected static void access(Object obj, int operation, int methodId)
	{
		objectCallbackState.access(obj , operation , methodId);
	}
	
	
	public static void createDelegate(Object delegate , Object obj)
	{
		DelegateRepository.put(delegate, obj , objectCallbackState );
	}
	
	
	
//	public static Set  entrySet(HashMap hashMap, int methodId) {
//	       
//		Set entrySet =  hashMap.entrySet();
//		
//		
//		DelegateRepository.put(entrySet, objectCallbackState.getObjectState(hashMap), objectCallbackState.fieldId);
//		
//		return entrySet;
//    }
	
	
}
