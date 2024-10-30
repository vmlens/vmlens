package com.vmlens.trace.agent.bootstrap.callbackdeprecated.obj;

import java.util.Iterator;
import java.util.Set;

public class SetCallback {

	protected static void access(Set obj, int isReadWrite, int methodId) {
	
		
		HashSetCallback.access(obj, isReadWrite, methodId);
		
	}
	
	
	protected static void createDelegate(Iterator result, Set obj) {
	
		
		if( obj instanceof java.util.HashSet)
		{
			HashSetCallback.createDelegate(result, obj);
		}
		else
		{
			DelegateTarget delegateTarget =  DelegateRepository.getDelegate(obj);
			
			if(delegateTarget != null)
			{
				DelegateRepository.put4DelegateTarget(result, delegateTarget);
			}
			
			
		}
		
		

		
	}
}
